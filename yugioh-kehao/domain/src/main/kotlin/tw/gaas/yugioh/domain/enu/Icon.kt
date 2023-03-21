package tw.gaas.yugioh.domain.enu

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
}
