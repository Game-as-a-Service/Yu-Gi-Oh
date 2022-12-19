package tw.gaas.yugioh.domain.enu

/**
 * 戰鬥位置，為怪獸卡特有的屬性
 * - ATTACK: 攻擊表示
 * - DEFENSE: 防護表示
 * - NOT_PLACED：尚未放置於決鬥場
 */
enum class BattlePosition(private val value: String) {
    ATTACK("ATTACK"),
    DEFENSE("DEFENSE"),
    NOT_PLACED("NOT_PLACED");
}
