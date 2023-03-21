package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 遊戲微服務計劃的用戶
 * @param id 用戶唯一識別值
 * @param title 用戶頭銜
 */
@Data
class User(
    private val id: String,
    private val title: String?
)
