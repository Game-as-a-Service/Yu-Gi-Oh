package tw.gaas.yugioh.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.enu.TrapType;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TrapCard extends Card {

    // 陷阱卡類型
    private TrapType trapType;
}
