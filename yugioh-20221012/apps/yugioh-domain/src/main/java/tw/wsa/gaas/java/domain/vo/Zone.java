package tw.wsa.gaas.java.domain.vo;

import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.vo.card.Card;
import tw.wsa.gaas.java.domain.vo.card.MonsterCard;
import tw.wsa.gaas.java.domain.vo.cards.Deck;
import tw.wsa.gaas.java.domain.vo.cards.GraveYardCards;
import tw.wsa.gaas.java.domain.vo.cards.MonsterCards;
import tw.wsa.gaas.java.domain.vo.cards.SpellAndTrapCards;

/**
 * 遊戲區
 */
public class Zone {

    // 玩家
    private final Duelist duelist;
    // 主要怪獸區
    private final MonsterCards monsterCards;
    // 魔法陷阱區
    private final SpellAndTrapCards spellAndTrapCards;
    // 墓地
    private final GraveYardCards graveYardCards;
    // 牌堆
    private final Deck deck;

    public Zone(Duelist duelist) {
        this.duelist = duelist;
        this.monsterCards = new MonsterCards();
        this.spellAndTrapCards = new SpellAndTrapCards();
        this.graveYardCards = new GraveYardCards();
        this.deck = new Deck();
    }

    public void start() {
        deck.shuffling();
        for (int i = 0; i < 6; i++) {
            duelist.drawCards(deck.draw());
        }
    }

    public void duelistDraw() {
        duelist.drawCards(deck.draw());
    }

    public void duelistSummonMonster(String uuid, CardState cardState) {
        Card card = duelist.summonMonster(uuid);
        monsterCards.summon(card, cardState);
    }

    public void duelistApplySpell(String uuid) {
        Card card = duelist.applySpell(uuid);

        // TODO: apply effect
    }

    public void duelistCoverTrap(String uuid, CardState cardState) {
        final Card card = duelist.coverTrap(uuid);
        spellAndTrapCards.cover(card, cardState);
    }

    public void duelistStartBattle(String uuid, Zone zone) {
        final MonsterCard monsterCard = (MonsterCard) monsterCards.startBattle(uuid);
        final MonsterCard target = zone.monsterCards.chooseTarget();

        final Integer scoreDelta = monsterCard.attacking(target);
        if (scoreDelta > 0) {
            if (null != target) {
                zone.monsterCards.moveToGraveYard(target);
                zone.graveYardCards.put(target);
            }
            zone.duelist.startBattle(scoreDelta);
        } else if (scoreDelta < 0) {
            this.monsterCards.moveToGraveYard(monsterCard);
            this.graveYardCards.put(monsterCard);
            duelist.startBattle(scoreDelta);
        } else {
            zone.monsterCards.moveToGraveYard(target);
            zone.graveYardCards.put(target);
            this.monsterCards.moveToGraveYard(monsterCard);
            this.graveYardCards.put(monsterCard);
        }
    }

    public Duelist getDuelist() {
        return duelist;
    }
}
