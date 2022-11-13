package tw.gaas.yugioh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    // 玩家
    private Player player;
    // 主要怪獸區
    private Cards mainMonster;
    // 魔法陷阱區
    private Cards spellAndTrap;
    // 墓地
    private Cards graveYard;
    // 牌堆
    private Cards deck;
}
