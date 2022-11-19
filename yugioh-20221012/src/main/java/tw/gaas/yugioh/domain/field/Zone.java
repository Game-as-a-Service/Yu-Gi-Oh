package tw.gaas.yugioh.domain.field;

import lombok.ToString;

@ToString
public class Zone {

    // 玩家
    private Duelist duelist;
    // 主要怪獸區
    private MonsterCards monsterCards;
    // 魔法陷阱區
    private SpellAndTrapCards spellAndTrapCards;
    // 墓地
    private GraveYardCards graveYardCards;
    // 牌堆
    private Deck deck;

    public Zone(Duelist duelist) {
        this.duelist = duelist;
        this.monsterCards = new MonsterCards();
        this.spellAndTrapCards = new SpellAndTrapCards();
        this.graveYardCards = new GraveYardCards();
        this.deck = new Deck();
    }

    public void setup() {
        deck.drawFirstSixCards(duelist);
    }
}
