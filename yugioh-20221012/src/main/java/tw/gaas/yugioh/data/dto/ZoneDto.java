package tw.gaas.yugioh.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDto {

    private DuelistDto duelist;

    private CardsDto monsterCards;

    private CardsDto spellAndTrapCards;

    private CardsDto graveYardCards;

    private CardsDto deck;
}
