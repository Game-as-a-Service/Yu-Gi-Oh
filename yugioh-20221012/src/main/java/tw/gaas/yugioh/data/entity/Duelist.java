package tw.gaas.yugioh.data.entity;

import lombok.ToString;
import tw.gaas.yugioh.data.dto.DuelistDto;
import tw.gaas.yugioh.data.entity.card.Card;
import tw.gaas.yugioh.data.entity.card.HandCards;

import java.util.Arrays;

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

    public DuelistDto toDto(Boolean isDuelist) {
        return DuelistDto
                .builder()
                .name(name)
                .lp(lp)
                .handCards(handCards.toDto(isDuelist))
                .build();
    }
}
