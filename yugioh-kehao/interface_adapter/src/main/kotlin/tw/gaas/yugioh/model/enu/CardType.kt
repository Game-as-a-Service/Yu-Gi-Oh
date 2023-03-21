package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

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

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): CardType? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
