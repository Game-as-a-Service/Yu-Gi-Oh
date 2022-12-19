package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 主要怪獸區
 * @param count 卡片數量
 * @param cards 卡片集合
 */
@Data
class MainMonsterZone(
    private var count: Int = 0,
    private var cards: Cards = Cards()
)
