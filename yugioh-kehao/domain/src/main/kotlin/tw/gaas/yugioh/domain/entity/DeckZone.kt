package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 牌組區
 * @param count 卡片數量
 * @param cards 卡片集合
 */
@Data
class DeckZone(
    private var count: Int = 0,
    private val cards: Cards = Cards()
)
