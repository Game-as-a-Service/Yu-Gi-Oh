package tw.gaas.yugioh.domain.card;

import tw.gaas.yugioh.domain.card.enu.Attribute;
import tw.gaas.yugioh.domain.card.enu.MonsterType;

public class MonsterCard extends Card {

    // 屬性
    private Attribute attribute;
    // 種族
    private MonsterType monsterType;
    // 等級
    private Integer rank;
    // 攻擊
    private Integer attack;
    // 防禦
    private Integer defense;
}
