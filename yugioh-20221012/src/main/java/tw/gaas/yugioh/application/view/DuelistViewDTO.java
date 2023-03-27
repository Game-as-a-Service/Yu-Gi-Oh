package tw.gaas.yugioh.application.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelistViewDTO {

    private String name;

    private Integer lp;

    private CardsViewDTO handCards;
}
