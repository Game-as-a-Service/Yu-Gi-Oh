package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated
import java.util.*

/**
 * 遊戲微服務計劃的用戶
 */
@Schema(description = "遊戲微服務計劃的用戶")
@Validated
@Data
class User {
    /**
     * 用戶唯一識別值
     * @return id
     */
    @get:Schema(description = "用戶唯一識別值")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Get title
     * @return title
     */
    @get:Schema(description = "稱謂")
    @JsonProperty("title")
    var title: String? = null
    fun id(id: String?): User {
        this.id = id
        return this
    }
}
