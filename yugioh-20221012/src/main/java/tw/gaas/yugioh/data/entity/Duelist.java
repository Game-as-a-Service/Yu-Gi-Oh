package tw.gaas.yugioh.data.entity;

import lombok.ToString;
import tw.gaas.yugioh.data.dto.DuelistDto;
import tw.gaas.yugioh.data.entity.card.Card;
import tw.gaas.yugioh.data.entity.card.HandCards;

import java.util.Arrays;

@ToString
public class Duelist {

    // 名稱
    private String name;
    // 生命值
    private Integer lp;
    // 手牌
    private HandCards handCards;

    public Duelist(String name) {
        this.name = name;
        this.lp = 0;
        this.handCards = new HandCards();
    }

    public String getName() {
        return name;
    }

    public void initLp() {
        this.lp = 4000;
    }

    public void drawCards(Card... draw) {
        Arrays.stream(draw).forEach(v -> handCards.draw(v));
    }

    public boolean validIsDuelist(String username) {
        return this.name.equals(username);
    }

    public boolean validIsDuelistMonsterHandCard(String uuid) {
        return handCards.containsThisMonsterCard(uuid);
    }

    public Card summonMonster(String uuid) {
        return handCards.submit(uuid);
    }

    public boolean validIsDuelistSpellHandCard(String uuid) {
        return handCards.containsThisSpellCard(uuid);
    }

    public Card applySpell(String uuid) {
        return handCards.submit(uuid);
    }

    public boolean validIsDuelistTrapHandCard(String uuid) {
        return handCards.containsThisTrapCard(uuid);
    }

    public Card coverTrap(String uuid) {
        return handCards.submit(uuid);
    }

    public void updateScore(Integer scoreDelta) {
        this.lp = this.lp - scoreDelta;
    }

    public DuelistDto toDto(Boolean isDuelist) {
        return DuelistDto
                .builder()
                .name(name)
                .lp(lp)
                .handCards(handCards.toDto(isDuelist))
                .build();
    }
}
