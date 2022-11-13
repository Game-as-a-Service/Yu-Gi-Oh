package tw.gaas.yugioh.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.enu.Type;

@Data
@SuperBuilder
@NoArgsConstructor
abstract class Card {

    // 名稱
    protected String name;
    // 類型
    protected Type type;
    // 描述
    protected String description;
}
