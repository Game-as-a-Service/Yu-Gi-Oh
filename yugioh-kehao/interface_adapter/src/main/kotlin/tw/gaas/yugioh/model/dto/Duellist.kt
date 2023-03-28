package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * 決鬥者，任何人只要有 300 萬美金都能參加決鬥
 */
@Schema(description = "決鬥者")
@Validated
@Data
class Duellist {
    /**
     * Get user
     * @return user
     */
    @get:Schema(required = true, description = "遊戲微服務計劃的用戶")
    @JsonProperty("user")
    var user: User? = null

    @JsonProperty("lifePoints")
    private var lifePoints = 8000

    /**
     * Get lifePoints
     * minimum: 0
     * maximum: 65535
     * @return lifePoints
     */
    @Schema(required = true, description = "生命點數")
    fun getLifePoints(): @NotNull @Min(0) @Max(65535) Int? {
        return lifePoints
    }
}
