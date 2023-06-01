package tw.wsa.gaas.domain.vo.card;

import tw.wsa.gaas.domain.enu.State;
import tw.wsa.gaas.domain.enu.Type;

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

    public String getUuid() {
        return uuid;
    }

    public Type getType() {
        return type;
    }
}
