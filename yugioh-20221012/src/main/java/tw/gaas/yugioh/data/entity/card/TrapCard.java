package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.data.dto.CardDto;
import tw.gaas.yugioh.data.enu.State;
import tw.gaas.yugioh.data.enu.TrapType;

import java.util.UUID;

@SuperBuilder
@ToString(callSuper = true)
public class TrapCard extends Card {

    // 陷阱卡類型
    protected TrapType trapType;

    @Override
    public Card copy() {
        return TrapCard
                .builder()
                .uuid(UUID.randomUUID().toString())
                .state(state)
                .name(name)
                .type(type)
                .description(description)
                .trapType(trapType)
                .build();
    }

    @Override
    public CardDto toDto(Boolean isDuelist) {
        if ((null != isDuelist && isDuelist) || state == State.FRONT) {
            return CardDto
                    .builder()
                    .name(name)
                    .uuid(uuid)
                    .state(state)
                    .type(type)
                    .description(description)
                    .attribute(null)
                    .monsterType(null)
                    .spellType(null)
                    .trapType(trapType)
                    .rank(null)
                    .attack(null)
                    .defense(null)
                    .build();
        } else {
            return CardDto
                    .builder()
                    .uuid(uuid)
                    .state(state)
                    .build();
        }
    }
}
