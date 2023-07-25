package tw.wsa.gaas.java.domain.vo.cards;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.vo.card.Card;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Random;

/**
 * 卡組
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Deck extends Cards {

    private transient final Random random = new SecureRandom();

    public Deck() {
        super(60, new LinkedList<>());
    }

    public void shuffling() {
        for (int i = 0; i < 10; i++) {
            elements.offer(libraries.get(random.nextInt(libraries.size())));
        }
    }

    public Card draw() {
        return elements.poll();
    }
}
