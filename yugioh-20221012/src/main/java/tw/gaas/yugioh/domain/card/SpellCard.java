package tw.gaas.yugioh.domain.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.card.enu.SpellType;
import tw.gaas.yugioh.domain.card.enu.Type;
import tw.gaas.yugioh.domain.dto.CardDto;

@SuperBuilder
@ToString(callSuper = true)
public class SpellCard extends Card {

    public static final SpellCard MONSTER_REBORN =
            SpellCard
                    .builder()
                    .name("死者蘇生")
                    .type(Type.SPELL)
                    .description("自分または相手の墓地のモンスター１体を対象として発動できる。そのモンスターを自分フィールドに特殊召喚する。")
                    .spellType(SpellType.NORMAL)
                    .build();

    // 魔法卡類型
    private SpellType spellType;

    @Override
    public Card copy() {
        return SpellCard
                .builder()
                .name(name)
                .type(type)
                .description(description)
                .spellType(spellType)
                .build();
    }

    @Override
    public CardDto toDto() {
        return CardDto
                .builder()
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
    }
}
