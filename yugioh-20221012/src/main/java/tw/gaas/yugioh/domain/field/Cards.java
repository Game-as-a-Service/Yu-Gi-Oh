package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;

import java.util.LinkedList;

@ToString
public abstract class Cards {

    // 上限
    protected Integer limit;
    // 元素
    protected LinkedList<Card> elements;
}
