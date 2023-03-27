package tw.gaas.yugioh.domain.vo;

import lombok.ToString;
import tw.gaas.yugioh.domain.enu.Type;

import java.util.function.Function;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class HandCards extends Cards {

    public HandCards() {
        limit = 6;
    }

    public void draw(Card card) {
        elements.offer(card);
    }

    public boolean containsThisMonsterCard(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(v -> v.uuid, Function.identity()))
                .get(uuid);

        return null != card && card.type == Type.MONSTER;
    }

    public Card submit(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(v -> v.uuid, Function.identity()))
                .get(uuid);
        elements.remove(card);

        return card;
    }

    public boolean containsThisSpellCard(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(v -> v.uuid, Function.identity()))
                .get(uuid);

        return null != card && card.type == Type.SPELL;
    }

    public boolean containsThisTrapCard(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(v -> v.uuid, Function.identity()))
                .get(uuid);

        return null != card && card.type == Type.TRAP;
    }
}
