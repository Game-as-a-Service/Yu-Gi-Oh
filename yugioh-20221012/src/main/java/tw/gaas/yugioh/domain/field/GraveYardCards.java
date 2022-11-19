package tw.gaas.yugioh.domain.field;


import lombok.ToString;

import java.util.LinkedList;

@ToString(callSuper = true)
public class GraveYardCards extends Cards {

    public GraveYardCards() {
        limit = -1;
        elements = new LinkedList<>();
    }
}
