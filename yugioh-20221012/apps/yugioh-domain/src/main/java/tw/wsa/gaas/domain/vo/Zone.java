package tw.wsa.gaas.domain.vo;

import tw.wsa.gaas.domain.enu.State;
import tw.wsa.gaas.domain.vo.card.Card;
import tw.wsa.gaas.domain.vo.card.MonsterCard;
import tw.wsa.gaas.domain.vo.cards.Deck;
import tw.wsa.gaas.domain.vo.cards.GraveYardCards;
import tw.wsa.gaas.domain.vo.cards.MonsterCards;
import tw.wsa.gaas.domain.vo.cards.SpellAndTrapCards;

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
        duelist.drawCards(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw());
    }

    public Boolean validDuelist(String username) {
        return duelist.validDuelist(username);
    }

    public Boolean validDuelistMonsterHandCard(String uuid) {
        return duelist.validDuelistMonsterHandCard(uuid);
    }

    public Boolean validDuelistSpellHandCard(String uuid) {
        return duelist.validDuelistSpellHandCard(uuid);
    }

    public Boolean validDuelistTrapHandCard(String uuid) {
        return duelist.validDuelistTrapHandCard(uuid);
    }

    public Boolean validDuelistMonsterCard(String uuid) {
        return monsterCards.validIsDuelistMonsterCard(uuid);
    }

    public void duelistDraw() {
        duelist.drawCards(deck.draw());
    }

    public void duelistSummonMonster(
            String uuid,
            State state
    ) {
        Card card = duelist.summonMonster(uuid);
        monsterCards.summon(card, state);
    }

    public void duelistApplySpell(String uuid) {
        Card card = duelist.applySpell(uuid);

        // TODO: apply effect
    }

    public void duelistCoverTrap(
            String uuid,
            State state
    ) {
        final Card card = duelist.coverTrap(uuid);
        spellAndTrapCards.cover(card, state);
    }

    public void duelistStartBattle(
            String uuid,
            Zone zone
    ) {
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
