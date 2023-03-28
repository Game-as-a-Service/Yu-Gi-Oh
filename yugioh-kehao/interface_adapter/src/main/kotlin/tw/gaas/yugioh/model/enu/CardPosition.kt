package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 卡片位置
 * - FACE_UP: 表側表示
 * - FACE_DOWN：裡側表示
 * - NOT_PLACED：尚未放置於決鬥場
 */
enum class CardPosition(private val value: String) {
    FACE_UP("FACE_UP"),
    FACE_DOWN("FACE_DOWN"),
    NOT_PLACED("NOT_PLACED");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): CardPosition? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
