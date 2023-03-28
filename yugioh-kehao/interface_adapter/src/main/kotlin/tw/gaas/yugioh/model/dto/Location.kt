package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import tw.gaas.yugioh.model.enu.BattlePosition
import tw.gaas.yugioh.model.enu.CardPosition
import tw.gaas.yugioh.model.enu.DuelFieldZone
import java.util.*
import javax.annotation.Generated

/**
 * 卡片所在區域
 */
@Schema(description = "卡片所在區域")
@Validated
@Data
class Location {
    /**
     * Get zone
     * @return zone
     */
    @get:Schema(description = "決鬥場區域")
    @JsonProperty("zone")
    var zone: DuelFieldZone? = null

    /**
     * Get cardPosition
     * @return cardPosition
     */
    @get:Schema(description = "卡片位置")
    @JsonProperty("cardPosition")
    var cardPosition: CardPosition? = null

    /**
     * Get battlePosition
     * @return battlePosition
     */
    @get:Schema(description = "戰鬥位置 (怪獸卡)")
    @JsonProperty("battlePosition")
    var battlePosition: BattlePosition? = null
}
