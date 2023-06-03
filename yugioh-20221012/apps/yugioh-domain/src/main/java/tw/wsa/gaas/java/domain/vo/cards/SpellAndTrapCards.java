package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.enu.State;
import tw.wsa.gaas.java.domain.vo.card.Card;

/**
 * 魔法陷阱卡組
 */
public class SpellAndTrapCards extends Cards {

    public SpellAndTrapCards() {
        limit = 5;
    }

    public void cover(Card card, State state) {
        card.changeState(state);

        elements.add(card);
    }
}
