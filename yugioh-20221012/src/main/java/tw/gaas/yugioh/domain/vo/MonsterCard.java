package tw.gaas.yugioh.domain.vo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.application.view.CardViewDTO;
import tw.gaas.yugioh.domain.enu.Attribute;
import tw.gaas.yugioh.domain.enu.MonsterType;
import tw.gaas.yugioh.domain.enu.State;

import java.util.UUID;

@SuperBuilder
@ToString(callSuper = true)
public class MonsterCard extends Card {

    // 屬性
    protected Attribute attribute;
    // 種族
    protected MonsterType monsterType;
    // 等級
    protected Integer rank;
    // 攻擊
    protected Integer attack;
    // 防禦
    protected Integer defense;

    public Integer battle(MonsterCard target) {
        return (null == target) ? this.attack : this.attack - target.attack;
    }

    @Override
    public Card copy() {
        return MonsterCard
                .builder()
                .uuid(UUID.randomUUID().toString())
                .state(state)
                .name(name)
                .type(type)
                .description(description)
                .attribute(attribute)
                .monsterType(monsterType)
                .rank(rank)
                .attack(attack)
                .defense(defense)
                .build();
    }

    @Override
    public CardViewDTO toDto(Boolean isDuelist) {
        if ((null != isDuelist && isDuelist) || state == State.FRONT) {
            return CardViewDTO
                    .builder()
                    .uuid(uuid)
                    .state(state)
                    .name(name)
                    .type(type)
                    .description(description)
                    .attribute(attribute)
                    .monsterType(monsterType)
                    .spellType(null)
                    .trapType(null)
                    .rank(rank)
                    .attack(attack)
                    .defense(defense)
                    .build();
        } else {
            return CardViewDTO.builder()
                    .uuid(uuid)
                    .state(state)
                    .build();
        }
    }
}
