package tw.gaas.yugioh.domain.field;

import lombok.ToString;

import java.util.LinkedList;

@ToString(callSuper = true)
public class SpellAndTrapCards extends Cards {

    public SpellAndTrapCards() {
        limit = 5;
        elements = new LinkedList<>();
    }
}
