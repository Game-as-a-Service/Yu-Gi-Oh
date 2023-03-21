package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * 牌組區
 * - 我方決鬥場：卡片數量
 * - 敵方決鬥場：卡片數量
 */
@Schema(description = "牌組區 " +
        "- 我方決鬥場：卡片數量 " +
        "- 敵方決鬥場：卡片數量")
@Validated
@Data
class DeckZone {
    @JsonProperty("count")
    private var count = 0

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
