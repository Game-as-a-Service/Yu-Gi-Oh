package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.vo.card.Card;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 手牌卡組
 */
public class HandCards extends Cards {

    public HandCards() {
        limit = 6;
    }

    public void draw(Card card) {
        elements.offer(card);
    }

    public Card submit(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(Card::getUuid, Function.identity()))
                .get(uuid);
        elements.remove(card);

        return card;
    }
}
