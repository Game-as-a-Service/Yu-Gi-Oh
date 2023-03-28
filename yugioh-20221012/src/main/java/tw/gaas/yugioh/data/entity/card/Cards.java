package tw.gaas.yugioh.data.entity.card;

import lombok.ToString;
import tw.gaas.yugioh.data.dto.CardsDto;
import tw.gaas.yugioh.data.enu.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public abstract class Cards {

    private static final MonsterCard BlueEyesShiningDragon =
            MonsterCard
                    .builder()
                    .state(State.BACK)
                    .name("青眼の白龍")
                    .type(Type.MONSTER)
                    .description("高い攻撃力を誇る伝説のドラゴン。どんな相手でも粉砕する、その破壊力は計り知れない。")
                    .attribute(Attribute.LIGHT)
                    .monsterType(MonsterType.DRAGON)
                    .rank(8)
                    .attack(3000)
                    .defense(2500)
                    .build();

    private static final MonsterCard DarkMagician =
            MonsterCard
                    .builder()
                    .state(State.BACK)
                    .name("ブラック・マジシャン")
                    .type(Type.MONSTER)
                    .description("魔法使いとしては、攻撃力・守備力ともに最高クラス。")
                    .attribute(Attribute.DARK)
                    .monsterType(MonsterType.SPELLCASTER)
                    .rank(7)
                    .attack(2500)
                    .defense(2100)
                    .build();

    private static final SpellCard MonsterReborn =
            SpellCard
                    .builder()
                    .state(State.BACK)
                    .name("死者蘇生")
                    .type(Type.SPELL)
                    .description("自分または相手の墓地のモンスター１体を対象として発動できる。そのモンスターを自分フィールドに特殊召喚する。")
                    .spellType(SpellType.NORMAL)
                    .build();


    private static final TrapCard MirrorForce =
            TrapCard
                    .builder()
                    .state(State.BACK)
                    .name("聖なるバリア －ミラーフォース－")
                    .type(Type.TRAP)
                    .description("相手モンスターの攻撃宣言時に発動できる。相手フィールドの攻撃表示モンスターを全て破壊する。")
                    .trapType(TrapType.NORMAL)
                    .build();

    protected static final List<Card> library = List.of(
            BlueEyesShiningDragon,
            DarkMagician,
            MonsterReborn,
            MirrorForce
    );

    // 上限
    protected Integer limit;
    // 元素
    protected LinkedList<Card> elements = new LinkedList<>();

    public CardsDto toDto(Boolean isDuelist) {
        return CardsDto
                .builder()
                .elements(elements.stream().map(v -> v.toDto(isDuelist)).collect(Collectors.toList()))
                .build();
    }
}
