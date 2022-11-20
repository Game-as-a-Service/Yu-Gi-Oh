package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;
import tw.gaas.yugioh.domain.dto.DeckDto;

import java.util.LinkedList;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class Deck extends Cards {

    public Deck() {
        limit = 60;
        elements = new LinkedList<>();
    }

    public DeckDto toDto() {
        return DeckDto
                .builder()
                .elements(elements.stream().map(Card::toDto).collect(Collectors.toList()))
                .build();
    }
}
