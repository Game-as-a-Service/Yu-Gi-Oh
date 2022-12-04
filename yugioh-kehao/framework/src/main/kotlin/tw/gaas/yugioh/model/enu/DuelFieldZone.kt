package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 決鬥場區域
 * - HAND: 手牌
 * - MAIN_MONSTER_ZONE: 主要怪獸區
 * - SPELL_TRAP_ZONE: 魔法和陷阱區
 * - FIELD_ZONE: 場地區
 * - GRAVEYARD: 墓地
 * - EXTRA_DECK_ZONE: 額外牌組區
 * - DECK_ZONE: 牌組區
 * - EXTRA_MONSTER_ZONE: 額外怪獸區
 */
enum class DuelFieldZone(private val value: String) {
    HAND("HAND"),
    MAIN_MONSTER_ZONE("MAIN_MONSTER_ZONE"),
    SPELL_TRAP_ZONE("SPELL_TRAP_ZONE"),
    FIELD_ZONE("FIELD_ZONE"),
    GRAVEYARD("GRAVEYARD"),
    EXTRA_DECK_ZONE("EXTRA_DECK_ZONE"),
    DECK_ZONE("DECK_ZONE"),
    EXTRA_MONSTER_ZONE("EXTRA_MONSTER_ZONE");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): DuelFieldZone? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
