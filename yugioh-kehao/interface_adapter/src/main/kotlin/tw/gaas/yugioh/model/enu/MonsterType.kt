package tw.gaas.yugioh.model.enu

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 怪獸種族
 * - Aqua
 * - Beast
 * - Beast-Warrior
 * - Creator God
 * - Cyberse
 * - Dinosaur
 * - Divine-Beast
 * - Dragon
 * - Fairy
 * - Fiend
 * - Fish
 * - Insect
 * - Machine
 * - Plant
 * - Psychic
 * - Pyro
 * - Reptile
 * - Rock
 * - Sea Serpent
 * - Spellcaster
 * - Thunder
 * - Warrior
 * - Winged Beast
 * - Wyrm
 * - Zombie
 */
enum class MonsterType(private val value: String) {
    AQUA("AQUA"),
    BEAST("BEAST"),
    BEAST_WARRIOR("BEAST_WARRIOR"),
    CREATOR_GOD("CREATOR_GOD"),
    CYBERSE("CYBERSE"),
    DINOSAUR("DINOSAUR"),
    DIVINE_BEAST("DIVINE_BEAST"),
    DRAGON("DRAGON"),
    FAIRY("FAIRY"),
    FIEND("FIEND"),
    FISH("FISH"),
    INSECT("INSECT"),
    MACHINE("MACHINE"),
    PLANT("PLANT"),
    PSYCHIC("PSYCHIC"),
    PYRO("PYRO"),
    REPTILE("REPTILE"),
    ROCK("ROCK"),
    SEA_SERPENT("SEA_SERPENT"),
    SPELLCASTER("SPELLCASTER"),
    THUNDER("THUNDER"),
    WARRIOR("WARRIOR"),
    WINGED_BEAST("WINGED_BEAST"),
    WYRM("WYRM"),
    ZOMBIE("ZOMBIE");

    @JsonValue
    override fun toString(): String {
        return value
    }

    companion object {
        @JsonCreator
        fun fromValue(text: String): MonsterType? {
            for (b in values()) {
                if (b.value == text) {
                    return b
                }
            }
            return null
        }
    }
}
