package tw.wsa.gaas.java.domain.vo.card;

import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;

public abstract class Card {

    // UUID
    protected String uuid;
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

    public String getUuid() {
        return uuid;
    }

    public CardType getType() {
        return cardType;
    }
}
