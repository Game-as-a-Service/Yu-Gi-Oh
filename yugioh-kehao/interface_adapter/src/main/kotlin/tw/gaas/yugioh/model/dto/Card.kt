package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import lombok.experimental.Accessors
import org.springframework.validation.annotation.Validated
import java.util.*

/**
 * 卡片
 */
@Schema(description = "卡片")
@Validated
@Accessors(chain = true)
@Data
class Card {
    /**
     * Get seq
     *
     * @return seq
     */
    @get:Schema(required = true, description = "卡片序號")
    @JsonProperty("seq")
    var seq: String? = null

    /**
     * Get location
     *
     * @return location
     */
    @get:Schema(required = true, description = "卡片位置")
    @JsonProperty("location")
    var location: Location? = null

    /**
     * Get info
     *
     * @return info
     */
    @get:Schema(description = "卡片資訊")
    @JsonProperty("info")
    var info: CardInfo? = null
}
