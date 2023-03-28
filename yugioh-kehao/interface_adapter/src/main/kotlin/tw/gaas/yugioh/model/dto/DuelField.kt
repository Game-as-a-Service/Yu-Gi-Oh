package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import java.util.*

/**
 * 決鬥場
 */
@Schema(description = "決鬥場")
@Validated
@Data
class DuelField {
    /**
     * Get duellist
     * @return duellist
     */
    @get:Schema(required = true, description = "決鬥者")
    @JsonProperty("duellist")
    var duellist: Duellist? = null

    /**
     * Get hand
     * @return hand
     */
    @get:Schema(description = "手牌")
    @JsonProperty("hand")
    var hand: Hand? = null

    /**
     * Get mainMonsterZone
     * @return mainMonsterZone
     */
    @get:Schema(description = "主要怪獸區")
    @JsonProperty("mainMonsterZone")
    var mainMonsterZone: MainMonsterZone? = null

    /**
     * Get spellTrapZone
     * @return spellTrapZone
     */
    @get:Schema(description = "魔法和陷阱區")
    @JsonProperty("spellTrapZone")
    var spellTrapZone: SpellTrapZone? = null

    /**
     * Get graveyard
     * @return graveyard
     */
    @get:Schema(description = "墓地")
    @JsonProperty("graveyard")
    var graveyard: Graveyard? = null

    /**
     * Get deckZone
     * @return deckZone
     */
    @get:Schema(description = "牌組區")
    @JsonProperty("deckZone")
    var deckZone: DeckZone? = null
}
