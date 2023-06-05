package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.vo.card.Card;

/**
 * 魔法陷阱卡組
 */
public class SpellAndTrapCards extends Cards {

    public SpellAndTrapCards() {
        limit = 5;
    }

    public void cover(Card card, CardState cardState) {
        card.changeState(cardState);

        elements.add(card);
    }
}
