package tw.wsa.gaas.java.domain.vo.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.TrapType;

import java.time.Instant;
import java.util.UUID;

/**
 * 陷阱卡
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrapCard extends Card {

    // 陷阱卡類型
    protected TrapType trapType;

    public TrapCard(
            Long uuid,
            CardState cardState,
            String name,
            CardType cardType,
            String description,
            TrapType trapType) {
        super(uuid, cardState, name, cardType, description);
        this.trapType = trapType;
    }

    @Override
    public Card copy() {
        return new TrapCard(
                Instant.now().toEpochMilli(),
                this.cardState,
                this.name,
                this.cardType,
                this.description,
                this.trapType);
    }
}
