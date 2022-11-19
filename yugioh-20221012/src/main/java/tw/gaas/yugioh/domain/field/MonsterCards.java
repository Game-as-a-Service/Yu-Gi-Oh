package tw.gaas.yugioh.domain.field;

import lombok.ToString;

import java.util.LinkedList;

@ToString(callSuper = true)
public class MonsterCards extends Cards {

    public MonsterCards() {
        limit = 5;
        elements = new LinkedList<>();
    }
}
