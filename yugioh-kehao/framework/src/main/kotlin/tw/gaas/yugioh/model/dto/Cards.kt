package tw.gaas.yugioh.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import org.springframework.validation.annotation.Validated

/**
 * 卡片集合，目前採取 array 形式存在
 */
@Schema(description = "卡片集合")
@Validated
@Data
class Cards : ArrayList<Card?>() {
}
