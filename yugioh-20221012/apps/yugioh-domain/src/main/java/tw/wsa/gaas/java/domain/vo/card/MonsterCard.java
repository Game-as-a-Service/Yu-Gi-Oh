package tw.wsa.gaas.java.domain.vo.card;

import tw.wsa.gaas.java.domain.enu.Attribute;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.MonsterType;

/**
 * 怪獸卡
 */
public class MonsterCard extends Card {

    // 屬性
    protected Attribute attribute;
    // 種族
    protected MonsterType monsterType;
    // 等級
    protected Integer rank;
    // 攻擊
    protected Integer attack;
    // 防禦
    protected Integer defense;

    public MonsterCard(
            String uuid,
            CardState cardState,
            String name,
            CardType cardType,
            String description,
            Attribute attribute,
            MonsterType monsterType,
            Integer rank,
            Integer attack,
            Integer defense) {
        super(uuid, cardState, name, cardType, description);
        this.attribute = attribute;
        this.monsterType = monsterType;
        this.rank = rank;
        this.attack = attack;
        this.defense = defense;
    }

    public Integer attacking(MonsterCard target) {
        return (null == target) ? this.attack : this.attack - target.attack;
    }

    public Integer getAttack() {
        return attack;
    }
}
