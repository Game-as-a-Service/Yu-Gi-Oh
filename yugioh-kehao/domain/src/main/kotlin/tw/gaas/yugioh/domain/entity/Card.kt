package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 卡片
 * @param seq 卡片序號
 * @param info 卡片資訊 (牌庫資料)
 * @param location 卡片所在區域
 */
@Data
class Card(
    private val seq: String,
    private val info: CardInfo,
    private val location: Location = Location()
)
