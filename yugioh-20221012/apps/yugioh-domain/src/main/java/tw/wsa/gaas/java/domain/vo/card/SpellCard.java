package tw.wsa.gaas.java.domain.vo.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.SpellType;

import java.time.Instant;
import java.util.UUID;

/**
 * 魔法卡
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SpellCard extends Card {

    // 魔法卡類型
    protected SpellType spellType;

    public SpellCard(
            Long uuid,
            CardState cardState,
            String name,
            CardType cardType,
            String description,
            SpellType spellType) {
        super(uuid, cardState, name, cardType, description);
        this.spellType = spellType;
    }

    @Override
    public Card copy() {
        return new SpellCard(
                Instant.now().toEpochMilli(),
                this.cardState,
                this.name,
                this.cardType,
                this.description,
                this.spellType);
    }
}
