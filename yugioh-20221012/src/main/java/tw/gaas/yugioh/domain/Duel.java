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
public class Duel {

    // 名稱
    private String name;
    // 手牌
    private List<Card> hand;
}
