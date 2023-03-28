package tw.gaas.yugioh.domain.entity

import lombok.Data
import tw.gaas.yugioh.domain.enu.BattlePosition
import tw.gaas.yugioh.domain.enu.CardPosition
import tw.gaas.yugioh.domain.enu.DuelFieldZone

/**
 * 卡片所在區域
 * @param zone 決鬥場區域
 * @param cardPosition 卡片位置
 * @param battlePosition 戰鬥位置
 */
@Data
class Location(
    private var zone: DuelFieldZone = DuelFieldZone.HAND,
    private var cardPosition: CardPosition = CardPosition.NOT_PLACED,
    private var battlePosition: BattlePosition = BattlePosition.NOT_PLACED
)
