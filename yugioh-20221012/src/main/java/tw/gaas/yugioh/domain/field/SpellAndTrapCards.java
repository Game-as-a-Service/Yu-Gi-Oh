package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;
import tw.gaas.yugioh.domain.dto.SpellAndTrapCardsDto;

import java.util.LinkedList;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class SpellAndTrapCards extends Cards {

    public SpellAndTrapCards() {
        limit = 5;
        elements = new LinkedList<>();
    }

    public SpellAndTrapCardsDto toDto() {
        return SpellAndTrapCardsDto
                .builder()
                .elements(elements.stream().map(Card::toDto).collect(Collectors.toList()))
                .build();
    }
}
