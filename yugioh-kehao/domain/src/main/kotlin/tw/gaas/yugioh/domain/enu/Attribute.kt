package tw.gaas.yugioh.domain.enu

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
}
