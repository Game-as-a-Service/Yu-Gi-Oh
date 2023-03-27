package tw.gaas.yugioh.domain.vo;

import lombok.ToString;
import tw.gaas.yugioh.domain.enu.State;

@ToString(callSuper = true)
public class SpellAndTrapCards extends Cards {

    public SpellAndTrapCards() {
        limit = 5;
    }

    public void cover(Card card, State state) {
        card.changeState(state);

        elements.add(card);
    }
}
