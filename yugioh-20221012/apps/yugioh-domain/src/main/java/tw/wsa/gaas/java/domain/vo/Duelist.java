package tw.wsa.gaas.java.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.vo.card.Card;
import tw.wsa.gaas.java.domain.vo.cards.HandCards;

import java.util.Arrays;

/**
 * 決鬥者
 */
@Getter
@EqualsAndHashCode
@ToString
public class Duelist {

    // 名稱
    private final String name;
    // 生命值
    private Integer lp;
    // 手牌
    private final HandCards handCards;

    public Duelist(String name) {
        this.name = name;
        this.lp = 4000;
        this.handCards = new HandCards();
    }

    public void drawCards(Card... draw) {
        Arrays.stream(draw).forEach(handCards::draw);
    }

    public Card summonMonster(Long uuid) {
        return handCards.submit(uuid);
    }

    public Card applySpell(Long uuid) {
        return handCards.submit(uuid);
    }

    public Card coverTrap(Long uuid) {
        return handCards.submit(uuid);
    }

    public void startBattle(Integer scoreDelta) {
        this.lp = this.lp - scoreDelta;
    }
}
