package tw.gaas.yugioh.domain

data class KDuelField(
    val left: KZone,
    val right: KZone,
)

data class KZone(
    val duel: KDuel,
    val mainMonster: List<KCard>,
    val spellAndTrap: List<KCard>,
    val graveYard: List<KCard>,
    val deck: List<Card>,
)

data class KDuel(
    val name: String,
    val hand: List<KCard>,
)