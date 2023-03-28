package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * 魔法和陷阱區
 * - 我方決鬥場：卡片數量以及所有的卡片完整資訊
 * - 敵方決鬥場：卡片數量以及表側表示的卡片完整資訊
 */
@Schema(description = "魔法和陷阱區 " +
        "- 我方決鬥場：卡片數量以及所有的卡片完整資訊 " +
        "- 敵方決鬥場：卡片數量以及表側表示的卡片完整資訊")
@Validated
@Data
class SpellTrapZone {
    @JsonProperty("count")
    private var count = 0

    /**
     * Get cards
     * @return cards
     */
    @get:Schema(description = "卡片列表")
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
