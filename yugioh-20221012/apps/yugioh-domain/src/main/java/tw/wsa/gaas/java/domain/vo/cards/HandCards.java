package tw.wsa.gaas.java.domain.vo.cards;

import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.vo.card.Card;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 手牌卡組
 */
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
                .collect(Collectors.toMap(Card::getUuid, Function.identity()))
                .get(uuid);

        return null != card && card.getType() == CardType.MONSTER;
    }

    public Card submit(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(Card::getUuid, Function.identity()))
                .get(uuid);
        elements.remove(card);

        return card;
    }

    public boolean containsThisSpellCard(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(Card::getUuid, Function.identity()))
                .get(uuid);

        return null != card && card.getType() == CardType.SPELL;
    }

    public boolean containsThisTrapCard(String uuid) {
        final Card card = elements
                .stream()
                .collect(Collectors.toMap(Card::getUuid, Function.identity()))
                .get(uuid);

        return null != card && card.getType() == CardType.TRAP;
    }
}
