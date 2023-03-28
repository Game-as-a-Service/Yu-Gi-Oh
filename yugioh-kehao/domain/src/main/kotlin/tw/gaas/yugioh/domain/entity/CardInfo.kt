package tw.gaas.yugioh.domain.entity

import tw.gaas.yugioh.domain.enu.Attribute
import tw.gaas.yugioh.domain.enu.CardType
import tw.gaas.yugioh.domain.enu.Icon
import tw.gaas.yugioh.domain.enu.MonsterType

/**
 * 卡片資訊 (牌庫資料)
 * @param id 遊戲王卡片編號
 * @param name 名稱
 * @param type 類別
 * @param description 敘述
 * @param attribute 屬性 (怪獸卡)
 * @param icon 效果標記 (魔法卡/陷阱卡)
 * @param monsterType 怪獸種族 (怪獸卡)
 * @param level 等級
 * @param attack 攻擊力
 * @param defense 守備力
 */
data class CardInfo(
    val id: String,
    val name: String,
    val type: CardType,
    val description: String,
    val attribute: Attribute?,
    val icon: Icon? = null,
    val monsterType: MonsterType? = null,
    val level: Int? = null,
    val attack: Int? = null,
    val defense: Int? = null
)
