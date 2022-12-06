package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;

import java.security.SecureRandom;
import java.util.Random;

@ToString(callSuper = true)
public class Deck extends Cards {

    private final Random random = new SecureRandom();

    public Deck() {
        limit = 60;
    }

    public void shuffling() {
        for (int i = 0; i < 10; i++) {
            elements.offer(library.get(random.nextInt(library.size())).copy());
        }
    }

    public Card draw() {
        return elements.poll();
    }
}
