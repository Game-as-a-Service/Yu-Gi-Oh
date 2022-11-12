package tw.gaas.yugioh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    // 決鬥者
    private Duel duel;
    // 主要怪獸區
    private List<Card> mainMonster;
    // 魔法陷阱區
    private List<Card> spellAndTrap;
    // 墓地
    private List<Card> graveYard;
    // 牌堆
    private List<Card> deck;
}
