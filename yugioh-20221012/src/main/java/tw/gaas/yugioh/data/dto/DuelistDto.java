package tw.gaas.yugioh.data.dto;

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

    private CardsDto handCards;
}
