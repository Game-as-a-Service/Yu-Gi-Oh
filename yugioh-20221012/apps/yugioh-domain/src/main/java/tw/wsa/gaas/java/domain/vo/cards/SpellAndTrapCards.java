package tw.wsa.gaas.java.domain.vo.cards;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.vo.card.Card;

import java.util.LinkedList;

/**
 * 魔法陷阱卡組
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SpellAndTrapCards extends Cards {

    public SpellAndTrapCards() {
        super(5, new LinkedList<>());
    }

    public void cover(Card card, CardState cardState) {
        card.changeState(cardState);

        elements.add(card);
    }
}
