package tw.gaas.yugioh.domain.card;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.gaas.yugioh.domain.card.enu.TrapType;
import tw.gaas.yugioh.domain.card.enu.Type;

@SuperBuilder
@ToString(callSuper = true)
public class TrapCard extends Card {

    public static final TrapCard MIRROR_FORCE =
            TrapCard
                    .builder()
                    .name("聖なるバリア －ミラーフォース－")
                    .type(Type.TRAP)
                    .description("相手モンスターの攻撃宣言時に発動できる。相手フィールドの攻撃表示モンスターを全て破壊する。")
                    .trapType(TrapType.NORMAL)
                    .build();

    // 陷阱卡類型
    private TrapType trapType;

    @Override
    public Card copy() {
        return TrapCard
                .builder()
                .name(name)
                .type(type)
                .description(description)
                .trapType(trapType)
                .build();
    }
}
