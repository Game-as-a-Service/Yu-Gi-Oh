package tw.wsa.gaas.java.domain.vo.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.vo.card.Card;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 卡組
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Deck extends Cards {

    @JsonIgnore
    private final Random random = new SecureRandom();

    public Deck() {
        super(60, new LinkedList<>());
    }

    public void shuffling() {
        for (int i = 0; i < 10; i++) {
            final Card card = libraries.get(random.nextInt(libraries.size())).copy();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            elements.offer(card);
        }
    }

    public Card draw() {
        return elements.poll();
    }
}
