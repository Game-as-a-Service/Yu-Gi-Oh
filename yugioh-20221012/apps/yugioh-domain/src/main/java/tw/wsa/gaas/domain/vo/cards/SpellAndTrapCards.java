package tw.wsa.gaas.domain.vo.cards;

import tw.wsa.gaas.domain.enu.State;
import tw.wsa.gaas.domain.vo.card.Card;
import tw.wsa.gaas.domain.vo.cards.Cards;

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
