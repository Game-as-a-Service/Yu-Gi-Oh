package tw.gaas.yugioh.domain.field;


import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;
import tw.gaas.yugioh.domain.dto.GraveYardCardsDto;

import java.util.LinkedList;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class GraveYardCards extends Cards {

    public GraveYardCards() {
        limit = -1;
        elements = new LinkedList<>();
    }

    public GraveYardCardsDto toDto() {
        return GraveYardCardsDto
                .builder()
                .elements(elements.stream().map(Card::toDto).collect(Collectors.toList()))
                .build();
    }
}
