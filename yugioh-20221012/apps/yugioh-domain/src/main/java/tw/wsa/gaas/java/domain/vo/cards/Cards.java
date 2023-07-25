package tw.wsa.gaas.java.domain.vo.cards;

import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class Cards {

    private static final MonsterCard BlueEyesShiningDragon =
            new MonsterCard(
                    0L,
                    CardState.BACK,
                    "Blue-Eyes White Dragon",
                    CardType.MONSTER,
                    "This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have faced this awesome creature and lived to tell the tale.",
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
}
