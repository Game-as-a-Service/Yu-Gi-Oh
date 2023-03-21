package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

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

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): BattlePosition? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
