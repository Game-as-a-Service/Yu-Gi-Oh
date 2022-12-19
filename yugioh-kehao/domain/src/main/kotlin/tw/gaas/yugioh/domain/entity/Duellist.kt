package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 決鬥者，任何人只要有 300 萬美金都能參加決鬥
 * @param user 遊戲微服務計劃的用戶
 * @param lifePoints 生命值
 */
@Data
class Duellist(
    private val user: User,
    private var lifePoints: Int = 8000
)
