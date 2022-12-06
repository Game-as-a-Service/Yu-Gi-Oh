package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;
import tw.gaas.yugioh.data.enu.State;

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
