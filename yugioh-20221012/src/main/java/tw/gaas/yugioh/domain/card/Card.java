package tw.gaas.yugioh.domain.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.card.enu.Type;

@SuperBuilder
@ToString
public abstract class Card {

    // 名稱
    protected String name;
    // 類型
    protected Type type;
    // 描述
    protected String description;

    public abstract Card copy();
}
