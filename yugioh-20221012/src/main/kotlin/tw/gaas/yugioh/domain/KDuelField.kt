package tw.gaas.yugioh.domain

import tw.gaas.yugioh.domain.card.KCards
import tw.gaas.yugioh.domain.card.enu.Phase

data class KDuelField(
    val left: KZone,
    val right: KZone,
    val phase: Phase,
)

data class KZone(
    val duelist: KDuelist,
    val mainMonster: KCards,
    val spellAndTrap: KCards,
    val graveYard: KCards,
    val deck: KCards,
)

data class KDuelist(
    val name: String,
    val lp: Int,
    val hand: KCards,
)