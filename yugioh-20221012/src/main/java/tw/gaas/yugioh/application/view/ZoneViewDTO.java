package tw.gaas.yugioh.application.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneViewDTO {

    private DuelistViewDTO duelist;

    private CardsViewDTO monsterCards;

    private CardsViewDTO spellAndTrapCards;

    private CardsViewDTO graveYardCards;

    private CardsViewDTO deck;
}
