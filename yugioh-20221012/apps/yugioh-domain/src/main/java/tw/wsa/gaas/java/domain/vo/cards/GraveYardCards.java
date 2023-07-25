package tw.wsa.gaas.java.domain.vo.cards;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.vo.card.MonsterCard;

import java.util.LinkedList;

/**
 * 墓地卡組
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GraveYardCards extends Cards {

    public GraveYardCards() {
        super(99, new LinkedList<>());
    }

    public void put(MonsterCard target) {
        elements.add(target);
    }
}
