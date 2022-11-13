package tw.gaas.yugioh.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.enu.Attribute;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MonsterCard extends Card {

    // 屬性
    private Attribute attribute;
    // 等級
    private Integer rank;
    // 攻擊
    private Integer attack;
    // 防禦
    private Integer defense;
}
