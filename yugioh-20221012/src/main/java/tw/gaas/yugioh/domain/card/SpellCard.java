package tw.gaas.yugioh.domain.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.card.enu.SpellType;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SpellCard extends Card {

    // 魔法卡類型
    private SpellType spellType;
}
