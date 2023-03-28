package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import tw.gaas.yugioh.model.enu.Phase
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.*

/**
 * 對局
 */
@Schema(description = "對局")
@Validated
@Data
class Game {
    /**
     * Get gameId
     * @return gameId
     */
    @get:Schema(required = true, description = "對局唯一識別值")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Get phase
     * @return phase
     */
    @get:Schema(required = true, description = "階段")
    @JsonProperty("phase")
    var phase: Phase? = null

    @JsonProperty("roundNumber")
    private var roundNumber = 0

    /**
     * Get myDuelField
     * @return myDuelField
     */
    @get:Schema(required = true, description = "我方決鬥場")
    @JsonProperty("myDuelField")
    var myDuelField: DuelField? = null

    /**
     * Get enemyDuelField
     * @return enemyDuelField
     */
    @get:Schema(description = "敵方決鬥場")
    @JsonProperty("enemyDuelField")
    var enemyDuelField: DuelField? = null

    /**
     * Get roundNumber
     * minimum: 0
     * maximum: 65535
     * @return roundNumber
     */
    @Schema(required = true, description = "回合數")
    fun getRoundNumber(): @NotNull @Valid @Min(0) @Max(65535) Int? {
        return roundNumber
    }
}
