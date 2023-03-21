package tw.gaas.yugioh.domain.entity

import lombok.Data
import tw.gaas.yugioh.domain.enu.Phase
import tw.gaas.yugioh.domain.error.DemoException
import tw.gaas.yugioh.domain.event.DomainEvent
import java.util.Vector

const val DEFAULT_DUEL_FIELD_SIZE = 2

/**
 * 對局
 * @param id 對局唯一識別值
 * @param phase 階段
 * @param roundNumber 回合數
 * @param duelFields 決鬥場 (目前僅支援雙人決鬥)
 */
@Data
class Game(
    private val id: String,
    private var phase: Phase = Phase.WAITING,
    private var roundNumber: Int = 0,
    private var duelFields : Vector<DuelField> = Vector<DuelField>(DEFAULT_DUEL_FIELD_SIZE)
) {
    /**
     * 加入遊戲
     * @param user 遊戲微服務計劃的用戶
     */
    fun join(user: User): DomainEvent {
        val isWaiting = phase == Phase.WAITING
        val notEnoughDuelists = duelFields.size < duelFields.capacity()
        if (!isWaiting || !notEnoughDuelists) {
            throw DemoException()
        }
        val duellist = Duellist(user)
        val duelField = DuelField(duellist)
        duelFields.add(duelField)

        val enoughDuelists = duelFields.size == duelFields.capacity()
        if (enoughDuelists) {
            phase = Phase.READY
        }
        // FIXME
        return DomainEvent()
    }
}
