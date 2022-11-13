package tw.gaas.yugioh.domain

data class KDuelField(
    val left: KZone,
    val right: KZone,
)

data class KZone(
    val duel: KPlayer,
    val mainMonster: KCards,
    val spellAndTrap: KCards,
    val graveYard: KCards,
    val deck: KCards,
)

data class KPlayer(
    val name: String,
    val lp: Int,
    val hand: KCards,
)