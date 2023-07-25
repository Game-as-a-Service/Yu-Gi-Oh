package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.vo.card.Card;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 卡組
 */
public class Deck extends Cards {

    private final Random random = new SecureRandom();

    public Deck() {
        limit = 60;
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
