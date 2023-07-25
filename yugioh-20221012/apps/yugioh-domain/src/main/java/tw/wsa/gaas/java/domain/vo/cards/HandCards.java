package tw.wsa.gaas.java.domain.vo.cards;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.vo.card.Card;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 手牌卡組
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HandCards extends Cards {

    public HandCards() {
        super(6, new LinkedList<>());
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
