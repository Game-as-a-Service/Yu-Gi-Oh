package tw.wsa.gaas.java.domain.vo.card;

import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.TrapType;

/**
 * 陷阱卡
 */
public class TrapCard extends Card {

    // 陷阱卡類型
    protected TrapType trapType;

    public TrapCard(
            String uuid,
            CardState cardState,
            String name,
            CardType cardType,
            String description,
            TrapType trapType) {
        super(uuid, cardState, name, cardType, description);
        this.trapType = trapType;
    }
}
