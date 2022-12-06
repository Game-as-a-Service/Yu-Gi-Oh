package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;
import tw.gaas.yugioh.data.enu.State;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@ToString(callSuper = true)
public class MonsterCards extends Cards {

    public MonsterCards() {
        limit = 5;
    }

    public void summon(Card card, State state) {
        card.changeState(state);

        elements.add(card);
    }

    public boolean validIsDuelistMonsterCard(String uuid) {
        return elements.stream().map(v -> v.uuid).collect(toList()).contains(uuid);
    }

    public Card startBattle(String uuid) {
        return elements
                .stream()
                .collect(Collectors.toMap(v -> v.uuid, Function.identity()))
                .get(uuid);
    }

    public MonsterCard chooseTarget() {
        return elements.size() > 0
                ? elements.stream().map(v -> (MonsterCard) v).sorted(Comparator.comparingInt(v -> v.attack)).collect(toList()).get(0)
                : null;
    }

    public void moveToGraveYard(Card card) {
        elements.remove(card);
    }
}
