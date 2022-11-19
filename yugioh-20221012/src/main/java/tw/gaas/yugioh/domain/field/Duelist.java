package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;

import java.util.Arrays;

@ToString
public class Duelist {

    // 名稱
    private String name;
    // 生命值
    private Integer lp;
    // 手牌
    private HandCards handCards;

    public Duelist(String name) {
        this.name = name;
        this.lp = 4000;
        this.handCards = new HandCards();
    }

    public void drawFirstSixCards(Card... draw) {
        Arrays.stream(draw).forEach(v -> handCards.elements.offer(v));
    }
}
