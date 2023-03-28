package tw.gaas.yugioh.domain.field

import tw.gaas.yugioh.data.enu.Phase
import tw.gaas.yugioh.domain.card.KCard

data class KDuelField(
    val left: KZone,
    val right: KZone,
    val phase: Phase,
)

data class KZone(
    val duelist: KDuelist,
    val monsterCards: KMonsterCards,
    val spellAndTrapCards: KSpellAndTrapCards,
    val graveYardCards: KGraveYardCards,
    val deck: KDeck,
)

data class KDuelist(
    val name: String,
    val lp: Int,
    val hand: KHandCards,
)

data class KMonsterCards(
    val limit: Int,
    val elements: List<KCard>,
)
data class KSpellAndTrapCards(
    val limit: Int,
    val elements: List<KCard>,
)

data class KGraveYardCards(
    val limit: Int,
    val elements: List<KCard>,
)

data class KDeck(
    val limit: Int,
    val elements: List<KCard>,
)

data class KHandCards(
    val limit: Int,
    val elements: List<KCard>,
)