package tw.gaas.yugioh.domain.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cards {

    // 上限
    private Integer limit;
    // 元素
    private List<Card> elements;
}
