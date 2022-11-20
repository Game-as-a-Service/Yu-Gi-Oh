package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;
import tw.gaas.yugioh.domain.dto.MonsterCardsDto;

import java.util.LinkedList;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class MonsterCards extends Cards {

    public MonsterCards() {
        limit = 5;
        elements = new LinkedList<>();
    }

    public MonsterCardsDto toDto() {
        return MonsterCardsDto
                .builder()
                .elements(elements.stream().map(Card::toDto).collect(Collectors.toList()))
                .build();
    }
}
