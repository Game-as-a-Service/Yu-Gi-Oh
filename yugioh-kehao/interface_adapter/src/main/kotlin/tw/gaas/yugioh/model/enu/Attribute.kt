package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 怪獸屬性
 * - DARK: 闇
 * - DIVINE: 神
 * - EARTH: 地
 * - FIRE: 炎
 * - LIGHT: 光
 * - WATER: 水
 * - WIND: 風
 */
enum class Attribute(private val value: String) {
    DARK("DARK"),
    DIVINE("DIVINE"),
    EARTH("EARTH"),
    FIRE("FIRE"),
    LIGHT("LIGHT"),
    WATER("WATER"),
    WIND("WIND");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): Attribute? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
