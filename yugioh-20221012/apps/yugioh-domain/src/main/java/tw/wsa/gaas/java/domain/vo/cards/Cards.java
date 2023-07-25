package tw.wsa.gaas.java.domain.vo.cards;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.Attribute;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.MonsterType;
import tw.wsa.gaas.java.domain.vo.card.Card;
import tw.wsa.gaas.java.domain.vo.card.MonsterCard;

import java.util.LinkedList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public abstract class Cards {

    private static final MonsterCard BlueEyesShiningDragon =
            new MonsterCard(
                    "",
                    CardState.BACK,
                    "青眼の白龍",
                    CardType.MONSTER,
                    "高い攻撃力を誇る伝説のドラゴン。どんな相手でも粉砕する、その破壊力は計り知れない。",
                    Attribute.LIGHT,
                    MonsterType.DRAGON,
                    8,
                    3000,
                    2500);

    protected static final List<Card> libraries = List.of(BlueEyesShiningDragon);

    // 上限
    protected Integer limit;
    // 元素
    protected LinkedList<Card> elements;

    public Cards(Integer limit, LinkedList<Card> elements) {
        this.limit = limit;
        this.elements = elements;
    }
}
