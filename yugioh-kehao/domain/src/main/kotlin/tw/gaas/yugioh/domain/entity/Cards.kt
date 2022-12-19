package tw.gaas.yugioh.domain.entity

import lombok.Data

/**
 * 卡片集合，目前採取 array 形式存在
 */
@Data
class Cards : ArrayList<Card?>()
