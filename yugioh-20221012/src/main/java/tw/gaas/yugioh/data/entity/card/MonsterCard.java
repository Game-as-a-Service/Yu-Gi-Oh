package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.data.dto.CardDto;
import tw.gaas.yugioh.data.enu.Attribute;
import tw.gaas.yugioh.data.enu.MonsterType;
import tw.gaas.yugioh.data.enu.State;

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
    public CardDto toDto(Boolean isDuelist) {
        if ((null != isDuelist && isDuelist) || state == State.FRONT) {
            return CardDto
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
            return CardDto.builder()
                    .uuid(uuid)
                    .state(state)
                    .build();
        }
    }
}
