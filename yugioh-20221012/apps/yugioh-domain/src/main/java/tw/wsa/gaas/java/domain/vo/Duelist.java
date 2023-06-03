package tw.wsa.gaas.java.domain.vo;

import tw.wsa.gaas.java.domain.vo.card.Card;
import tw.wsa.gaas.java.domain.vo.cards.HandCards;

import java.util.Arrays;

/**
 * 決鬥者
 */
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

    public Boolean validDuelist(String username) {
        return this.name.equals(username);
    }

    public Boolean validDuelistMonsterHandCard(String uuid) {
        return handCards.containsThisMonsterCard(uuid);
    }

    public Boolean validDuelistSpellHandCard(String uuid) {
        return handCards.containsThisSpellCard(uuid);
    }

    public Boolean validDuelistTrapHandCard(String uuid) {
        return handCards.containsThisTrapCard(uuid);
    }

    public void drawCards(Card... draw) {
        Arrays.stream(draw).forEach(handCards::draw);
    }

    public Card summonMonster(String uuid) {
        return handCards.submit(uuid);
    }

    public Card applySpell(String uuid) {
        return handCards.submit(uuid);
    }

    public Card coverTrap(String uuid) {
        return handCards.submit(uuid);
    }

    public void startBattle(Integer scoreDelta) {
        this.lp = this.lp - scoreDelta;
    }

    public String getName() {
        return name;
    }
}
