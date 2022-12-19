package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 魔法和陷阱區
 * @param count 卡片數量
 * @param cards 卡片集合
 */
@Data
class SpellTrapZone(
    private var count: Int = 0,
    private val cards: Cards = Cards()
)
