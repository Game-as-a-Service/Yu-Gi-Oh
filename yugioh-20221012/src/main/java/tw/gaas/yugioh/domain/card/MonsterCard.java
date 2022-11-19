package tw.gaas.yugioh.domain.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.card.enu.Attribute;
import tw.gaas.yugioh.domain.card.enu.MonsterType;
import tw.gaas.yugioh.domain.card.enu.Type;

@SuperBuilder
@ToString(callSuper = true)
public class MonsterCard extends Card {

    public static final MonsterCard BlueEyesShiningDragon =
            MonsterCard
                    .builder()
                    .name("青眼の白龍")
                    .type(Type.MONSTER)
                    .description("高い攻撃力を誇る伝説のドラゴン。どんな相手でも粉砕する、その破壊力は計り知れない。")
                    .attribute(Attribute.LIGHT)
                    .monsterType(MonsterType.DRAGON)
                    .rank(8)
                    .attack(3000)
                    .defense(2500)
                    .build();

    // 屬性
    private Attribute attribute;
    // 種族
    private MonsterType monsterType;
    // 等級
    private Integer rank;
    // 攻擊
    private Integer attack;
    // 防禦
    private Integer defense;

    @Override
    public Card copy() {
        return MonsterCard
                .builder()
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
}
