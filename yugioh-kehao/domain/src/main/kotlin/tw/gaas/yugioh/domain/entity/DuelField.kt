package tw.gaas.yugioh.domain.entity

import lombok.Data
import java.util.*

/**
 * 決鬥場
 * @param duellist 決鬥者
 * @param hand 手牌
 * @param mainMonsterZone 主要怪獸區
 * @param spellTrapZone 魔法和陷阱區
 * @param graveyard 墓地
 * @param deckZone 牌組區
 */
@Data
class DuelField(
    private val duellist: Duellist,
    private val hand: Hand = Hand(),
    private val mainMonsterZone: MainMonsterZone = MainMonsterZone(),
    private val spellTrapZone: SpellTrapZone = SpellTrapZone(),
    private val graveyard: Graveyard = Graveyard(),
    private var deckZone: DeckZone = DeckZone()
)
