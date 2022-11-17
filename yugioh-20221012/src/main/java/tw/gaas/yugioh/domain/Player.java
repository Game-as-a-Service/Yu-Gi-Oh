package tw.gaas.yugioh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.domain.card.Cards;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    // 名稱
    private String name;
    // 生命值
    private Integer lp;
    // 手牌
    private Cards hand;
}
