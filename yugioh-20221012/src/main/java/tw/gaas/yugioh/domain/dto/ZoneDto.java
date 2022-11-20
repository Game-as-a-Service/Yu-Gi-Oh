package tw.gaas.yugioh.domain.dto;

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

    private MonsterCardsDto monsterCards;

    private SpellAndTrapCardsDto spellAndTrapCards;

    private GraveYardCardsDto graveYardCards;

    private DeckDto deck;
}
