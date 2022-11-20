package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.dto.DuelistDto;

@ToString
public class Duelist {

    // 名稱
    private String name;
    // 生命值
    private Integer lp;
    // 手牌
    private HandCards handCards;

    public DuelistDto toDto() {
        return DuelistDto
                .builder()
                .name(name)
                .lp(lp)
                .handCardsAmount(handCards.elements.size())
                .build();
    }
}
