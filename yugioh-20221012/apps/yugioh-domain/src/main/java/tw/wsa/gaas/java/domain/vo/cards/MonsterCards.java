package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.enu.State;
import tw.wsa.gaas.java.domain.vo.card.Card;
import tw.wsa.gaas.java.domain.vo.card.MonsterCard;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 怪獸卡組
 */
public class MonsterCards extends Cards {

    public MonsterCards() {
        limit = 5;
    }

    public void summon(Card card, State state) {
        card.changeState(state);

        elements.add(card);
    }

    public boolean validIsDuelistMonsterCard(String uuid) {
        return elements.stream().map(Card::getUuid).collect(toList()).contains(uuid);
    }

    public Card startBattle(String uuid) {
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
