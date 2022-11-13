package tw.gaas.yugioh.domain

import tw.gaas.yugioh.domain.enu.Attribute
import tw.gaas.yugioh.domain.enu.Icon
import tw.gaas.yugioh.domain.enu.Type

open class KCard(
    protected open val name: String,
    protected open val type: Type,
    protected open val icon: Icon,
    protected open val description: String,
)

data class KMonsterCard(
    val attribute: Attribute,
    val rank: Int,
    val attack: Int,
    val defense: Int,
    override val name: String,
    override val type: Type,
    override val icon: Icon,
    override val description: String,
) : KCard(name, type, icon, description)

data class KSpellCard(
    override val name: String,
    override val type: Type,
    override val icon: Icon,
    override val description: String,
) : KCard(name, type, icon, description)

data class KTrapCard(
    override val name: String,
    override val type: Type,
    override val icon: Icon,
    override val description: String,
) : KCard(name, type, icon, description)
