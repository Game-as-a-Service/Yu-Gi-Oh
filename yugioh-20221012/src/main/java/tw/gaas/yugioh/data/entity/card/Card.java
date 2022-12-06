package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.data.dto.CardDto;
import tw.gaas.yugioh.data.enu.State;
import tw.gaas.yugioh.data.enu.Type;

@SuperBuilder
@ToString
public abstract class Card {

    // UUID
    protected String uuid;
    // 表示狀態
    protected State state;
    // 名稱
    protected String name;
    // 類型
    protected Type type;
    // 描述
    protected String description;

    public void changeState(State state) {
        this.state = state;
    }

    public abstract Card copy();

    public abstract CardDto toDto(Boolean isDuelist);
}
