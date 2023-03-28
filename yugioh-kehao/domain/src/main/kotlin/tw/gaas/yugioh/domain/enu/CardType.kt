package tw.gaas.yugioh.domain.enu

/**
 * 卡片類別
 * - MONSTER: 怪獸卡
 * - SPELL: 魔法卡
 * - TRAP: 陷阱卡
 */
enum class CardType(private val value: String) {
    MONSTER("MONSTER"),
    SPELL("SPELL"),
    TRAP("TRAP");
}
