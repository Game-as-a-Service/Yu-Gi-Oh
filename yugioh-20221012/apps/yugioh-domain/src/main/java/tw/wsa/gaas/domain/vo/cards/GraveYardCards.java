package tw.wsa.gaas.domain.vo.cards;

import tw.wsa.gaas.domain.vo.card.MonsterCard;
import tw.wsa.gaas.domain.vo.cards.Cards;

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
