package tw.wsa.gaas.domain.vo.cards;

import tw.wsa.gaas.domain.vo.card.Card;

import java.util.LinkedList;
import java.util.List;

public abstract class Cards {

    protected static final List<Card> library = List.of();

    // 上限
    protected Integer limit;
    // 元素
    protected LinkedList<Card> elements = new LinkedList<>();
}
