package tw.gaas.yugioh.domain.vo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.application.view.CardViewDTO;
import tw.gaas.yugioh.domain.enu.State;
import tw.gaas.yugioh.domain.enu.TrapType;

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
    public CardViewDTO toDto(Boolean isDuelist) {
        if ((null != isDuelist && isDuelist) || state == State.FRONT) {
            return CardViewDTO
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
            return CardViewDTO
                    .builder()
                    .uuid(uuid)
                    .state(state)
                    .build();
        }
    }
}
