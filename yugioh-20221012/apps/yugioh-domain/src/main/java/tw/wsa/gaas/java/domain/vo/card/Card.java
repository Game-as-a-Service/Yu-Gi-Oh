package tw.wsa.gaas.java.domain.vo.card;

import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.State;

public abstract class Card {

    // UUID
    protected String uuid;
    // 表示狀態
    protected State state;
    // 名稱
    protected String name;
    // 類型
    protected CardType cardType;
    // 描述
    protected String description;

    public void changeState(State state) {
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public CardType getType() {
        return cardType;
    }
}
