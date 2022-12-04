package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 魔法卡/陷阱卡類型
 * - Normal: 通常
 * - Continuous: 永續
 * - Equip: 裝備
 * - Quick-Play: 速攻
 * - Field: 場地
 * - Ritual: 儀式
 * - Counter: 反制
 */
enum class Icon(private val value: String) {
    NORMAL("NORMAL"),
    CONTINUOUS("CONTINUOUS"),
    EQUIP("EQUIP"),
    QUICK_PLAY("QUICK_PLAY"),
    FIELD("FIELD"),
    RITUAL("RITUAL"),
    COUNTER("COUNTER");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): Icon? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
