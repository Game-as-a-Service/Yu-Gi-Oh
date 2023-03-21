package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import tw.gaas.yugioh.model.dto.Cards
import java.util.*
import javax.annotation.Generated
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * 手牌
 * - 我方決鬥場：卡片數量以及所有的卡片完整資訊
 * - 敵方決鬥場：卡片數量
 */
@Schema(description = "手牌 " +
        "- 我方決鬥場：卡片數量以及所有的卡片完整資訊 " +
        "- 敵方決鬥場：卡片數量")
@Validated
@Data
class Hand {
    @JsonProperty("count")
    private var count = 0

    /**
     * Get cards
     * @return cards
     */
    @get:Schema(description = "")
    @JsonProperty("cards")
    var cards: Cards? = null

    /**
     * Get count
     * minimum: 0
     * maximum: 99
     * @return count
     */
    @Schema(required = true, description = "卡片數量")
    fun getCount(): @Min(0) @Max(99) Int? {
        return count
    }
}
