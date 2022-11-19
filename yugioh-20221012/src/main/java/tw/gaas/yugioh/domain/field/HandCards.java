package tw.gaas.yugioh.domain.field;

import lombok.ToString;

import java.util.LinkedList;

@ToString(callSuper = true)
public class HandCards extends Cards {

    public HandCards() {
        limit = 6;
        elements = new LinkedList<>();
    }
}
