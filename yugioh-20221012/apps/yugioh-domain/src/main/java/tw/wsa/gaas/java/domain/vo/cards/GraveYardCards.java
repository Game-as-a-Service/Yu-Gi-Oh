package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.vo.card.MonsterCard;

/**
 * 墓地卡組
 */
public class GraveYardCards extends Cards {

    public GraveYardCards() {
        limit = 99;
    }

    public void put(MonsterCard target) {
        elements.add(target);
    }
}
