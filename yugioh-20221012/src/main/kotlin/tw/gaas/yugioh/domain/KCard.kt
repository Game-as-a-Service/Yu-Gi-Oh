package tw.gaas.yugioh.domain

import tw.gaas.yugioh.domain.enu.Attribute
import tw.gaas.yugioh.domain.enu.MonsterType
import tw.gaas.yugioh.domain.enu.SpellType
import tw.gaas.yugioh.domain.enu.TrapType
import tw.gaas.yugioh.domain.enu.Type

open class KCard(
    protected open val name: String,
    protected open val type: Type,
    protected open val description: String,
)

data class KCards(
    val limit: Int,
    val elements: List<KCard>,
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
