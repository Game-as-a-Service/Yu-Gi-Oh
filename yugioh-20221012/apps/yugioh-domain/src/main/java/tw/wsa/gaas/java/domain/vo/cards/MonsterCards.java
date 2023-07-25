package tw.wsa.gaas.java.domain.vo.cards;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.vo.card.Card;
import tw.wsa.gaas.java.domain.vo.card.MonsterCard;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 怪獸卡組
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonsterCards extends Cards {

    public MonsterCards() {
        super(5, new LinkedList<>());
    }

    public void summon(Card card, CardState cardState) {
        card.changeState(cardState);

        elements.add(card);
    }

    public Card startBattle(Long uuid) {
        return elements
                .stream()
                .collect(Collectors.toMap(Card::getUuid, Function.identity()))
                .get(uuid);
    }

    public MonsterCard chooseTarget() {
        return elements.size() > 0
                ? elements.stream().map(v -> (MonsterCard) v).sorted(Comparator.comparingInt(MonsterCard::getAttack)).collect(toList()).get(0)
                : null;
    }

    public void moveToGraveYard(Card card) {
        elements.remove(card);
    }
}
