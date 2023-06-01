package tw.wsa.gaas.domain.vo.cards;

import tw.wsa.gaas.domain.vo.card.Card;
import tw.wsa.gaas.domain.vo.cards.Cards;

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
            elements.offer(library.get(random.nextInt(library.size())));
        }
    }

    public Card draw() {
        return elements.poll();
    }
}
