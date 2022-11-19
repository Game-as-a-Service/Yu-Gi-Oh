package tw.gaas.yugioh.domain.card

import tw.gaas.yugioh.domain.card.enu.Attribute
import tw.gaas.yugioh.domain.card.enu.MonsterType
import tw.gaas.yugioh.domain.card.enu.SpellType
import tw.gaas.yugioh.domain.card.enu.TrapType
import tw.gaas.yugioh.domain.card.enu.Type

open class KCard(
    protected open val name: String,
    protected open val type: Type,
    protected open val description: String,
)

data class KMonsterCard(
    val attribute: Attribute,
    val monsterType: MonsterType,
    val rank: Int,
    val attack: Int,
    val defense: Int,
    override val name: String,
    override val type: Type,
    override val description: String,
) : KCard(name, type, description)

data class KSpellCard(
    val spellType: SpellType,
    override val name: String,
    override val type: Type,
    override val description: String,
) : KCard(name, type, description)

data class KTrapCard(
    val trapType: TrapType,
    override val name: String,
    override val type: Type,
    override val description: String,
) : KCard(name, type, description)
