package tw.gaas.yugioh.domain.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.card.enu.Attribute;
import tw.gaas.yugioh.domain.card.enu.MonsterType;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MonsterCard extends Card {

    // 屬性
    private Attribute attribute;
    // 種族
    private MonsterType monsterType;
    // 等級
    private Integer rank;
    // 攻擊
    private Integer attack;
    // 防禦
    private Integer defense;
}
