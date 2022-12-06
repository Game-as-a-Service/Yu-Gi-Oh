package tw.gaas.yugioh.data.entity;

import lombok.ToString;
import tw.gaas.yugioh.data.dto.ZoneDto;
import tw.gaas.yugioh.data.entity.card.Card;
import tw.gaas.yugioh.data.entity.card.Deck;
import tw.gaas.yugioh.data.entity.card.GraveYardCards;
import tw.gaas.yugioh.data.entity.card.MonsterCard;
import tw.gaas.yugioh.data.entity.card.MonsterCards;
import tw.gaas.yugioh.data.entity.card.SpellAndTrapCards;
import tw.gaas.yugioh.data.enu.State;

@ToString
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

    public Duelist getDuelist() {
        return duelist;
    }

    public void setup() {
        duelist.initLp();
        deck.shuffling();
        duelist.drawCards(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw());
    }

    public boolean validIsDuelist(String username) {
        return duelist.validIsDuelist(username);
    }

    public void duelistDraw() {
        duelist.drawCards(deck.draw());
    }

    public boolean validIsDuelistMonsterHandCard(String uuid) {
        return duelist.validIsDuelistMonsterHandCard(uuid);
    }

    public void duelistSummonMonster(String uuid, State state) {
        Card card = duelist.summonMonster(uuid);
        monsterCards.summon(card, state);
    }

    public boolean validIsDuelistSpellHandCard(String uuid) {
        return duelist.validIsDuelistSpellHandCard(uuid);
    }

    public void duelistApplySpell(String uuid) {
        Card card = duelist.applySpell(uuid);

        // TODO: apply effect
    }

    public boolean validIsDuelistTrapHandCard(String uuid) {
        return duelist.validIsDuelistTrapHandCard(uuid);
    }

    public void duelistCoverTrap(String uuid, State state) {
        final Card card = duelist.coverTrap(uuid);
        spellAndTrapCards.cover(card, state);
    }

    public boolean validIsDuelistMonsterCard(String uuid) {
        return monsterCards.validIsDuelistMonsterCard(uuid);
    }

    public void duelistStartBattle(String uuid, Zone zone) {
        final MonsterCard monsterCard = (MonsterCard) monsterCards.startBattle(uuid);
        final MonsterCard target = zone.monsterCards.chooseTarget();

        final Integer scoreDelta = monsterCard.battle(target);
        if (scoreDelta > 0) {
            if (null != target) {
                zone.monsterCards.moveToGraveYard(target);
                zone.graveYardCards.put(target);
            }
            zone.duelist.updateScore(scoreDelta);
        } else if (scoreDelta < 0) {
            this.monsterCards.moveToGraveYard(monsterCard);
            this.graveYardCards.put(monsterCard);
            duelist.updateScore(scoreDelta);
        } else {
            zone.monsterCards.moveToGraveYard(target);
            zone.graveYardCards.put(target);
            this.monsterCards.moveToGraveYard(monsterCard);
            this.graveYardCards.put(monsterCard);
        }
    }

    public ZoneDto toDto(Boolean isDuelist) {
        return ZoneDto
                .builder()
                .duelist(duelist.toDto(isDuelist))
                .monsterCards(monsterCards.toDto(null))
                .spellAndTrapCards(spellAndTrapCards.toDto(null))
                .graveYardCards(graveYardCards.toDto(null))
                .deck(deck.toDto(null))
                .build();
    }
}
