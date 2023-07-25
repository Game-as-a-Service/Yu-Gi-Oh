package tw.wsa.gaas.java.domain.vo.card;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class Card {

    // UUID
    protected Long uuid;
    // 表示狀態
    protected CardState cardState;
    // 名稱
    protected String name;
    // 類型
    protected CardType cardType;
    // 描述
    protected String description;

    public void changeState(CardState cardState) {
        this.cardState = cardState;
    }

    public abstract Card copy();
}
