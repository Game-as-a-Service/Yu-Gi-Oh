package tw.gaas.yugioh.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelistDto {

    private String name;

    private Integer lp;

    private Integer handCardsAmount;
}
