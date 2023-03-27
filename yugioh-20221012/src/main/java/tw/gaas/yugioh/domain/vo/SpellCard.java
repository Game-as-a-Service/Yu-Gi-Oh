package tw.gaas.yugioh.domain.vo;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.application.view.CardViewDTO;
import tw.gaas.yugioh.domain.enu.SpellType;
import tw.gaas.yugioh.domain.enu.State;

import java.util.UUID;

@SuperBuilder
@ToString(callSuper = true)
public class SpellCard extends Card {

    // 魔法卡類型
    protected SpellType spellType;

    @Override
    public Card copy() {
        return SpellCard
                .builder()
                .uuid(UUID.randomUUID().toString())
                .state(state)
                .name(name)
                .type(type)
                .description(description)
                .spellType(spellType)
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
                    .attribute(null)
                    .monsterType(null)
                    .spellType(spellType)
                    .trapType(null)
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