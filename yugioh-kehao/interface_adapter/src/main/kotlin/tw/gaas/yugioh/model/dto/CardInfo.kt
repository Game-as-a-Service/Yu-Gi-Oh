package tw.gaas.yugioh.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import tw.gaas.yugioh.model.enu.Icon
import tw.gaas.yugioh.model.enu.MonsterType
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Data
import lombok.experimental.Accessors
import org.springframework.validation.annotation.Validated
import tw.gaas.yugioh.model.enu.Attribute
import tw.gaas.yugioh.model.enu.CardType
import java.util.*

/**
 * 卡片資訊 (牌庫資料)
 */
@Schema(description = "遊戲王卡片資訊 (牌庫資料)")
@Validated
@Accessors(chain = true)
@Data
class CardInfo {
    /**
     * Get id
     * @return id
     */
    @get:Schema(description = "遊戲王卡片編號")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Get name
     * @return name
     */
    @get:Schema(description = "名稱")
    @JsonProperty("name")
    var name: String? = null

    /**
     * Get type
     * @return type
     */
    @get:Schema(description = "類別")
    @JsonProperty("type")
    var type: CardType? = null

    /**
     * Get description
     * @return description
     */
    @get:Schema(description = "敘述")
    @JsonProperty("description")
    var description: String? = null

    /**
     * Get attribute
     * @return attribute
     */
    @get:Schema(description = "屬性 (怪獸卡)")
    @JsonProperty("attribute")
    var attribute: Attribute? = null

    /**
     * Get icon
     * @return icon
     */
    @get:Schema(description = "效果標記 (魔法卡/陷阱卡)")
    @JsonProperty("icon")
    var icon: Icon? = null

    /**
     * Get monsterType
     * @return monsterType
     */
    @get:Schema(description = "怪獸種族 (怪獸卡)")
    @JsonProperty("monsterType")
    var monsterType: MonsterType? = null

    /**
     * Get level
     * minimum: 0
     * maximum: 12
     * @return level
     */
    @get:Schema(description = "等級")
    @JsonProperty("level")
    var level: UByte? = null

    /**
     * Get attack
     * minimum: 0
     * maximum: 65535
     * @return attack
     */
    @get:Schema(description = "攻擊力")
    @JsonProperty("attack")
    var attack: UShort? = null

    /**
     * Get defense
     * minimum: 0
     * maximum: 65535
     * @return defense
     */
    @get:Schema(description = "守備力")
    @JsonProperty("defense")
    var defense: UShort? = null
}
