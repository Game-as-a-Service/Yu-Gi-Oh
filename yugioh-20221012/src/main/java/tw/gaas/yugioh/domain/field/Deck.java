package tw.gaas.yugioh.domain.field;

import lombok.ToString;
import tw.gaas.yugioh.domain.card.Card;
import tw.gaas.yugioh.domain.card.MonsterCard;
import tw.gaas.yugioh.domain.card.SpellCard;
import tw.gaas.yugioh.domain.card.TrapCard;
import tw.gaas.yugioh.domain.dto.DeckDto;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class Deck extends Cards {

    private final Random random = new SecureRandom();
    private final List<Card> preDefinedCards = List.of(MonsterCard.BlueEyesShiningDragon, SpellCard.MONSTER_REBORN, TrapCard.MIRROR_FORCE);

    public Deck() {
        limit = 60;
        elements = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            elements.offer(preDefinedCards.get(random.nextInt(preDefinedCards.size())).copy());
        }
    }

    public void drawFirstSixCards(Duelist duelist) {
        duelist.drawFirstSixCards(draw(), draw(), draw(), draw(), draw(), draw());
    }

    public Card draw() {
        return elements.poll();
    }

    public DeckDto toDto() {
        return DeckDto
                .builder()
                .elements(elements.stream().map(Card::toDto).collect(Collectors.toList()))
                .build();
    }
}
