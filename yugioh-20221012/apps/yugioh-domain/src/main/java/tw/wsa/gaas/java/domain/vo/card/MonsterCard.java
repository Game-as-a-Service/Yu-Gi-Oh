package tw.wsa.gaas.java.domain.vo.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import tw.wsa.gaas.java.domain.enu.Attribute;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CardType;
import tw.wsa.gaas.java.domain.enu.MonsterType;

import java.time.Instant;
import java.util.UUID;

/**
 * 怪獸卡
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
            Long uuid,
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

    @Override
    public Card copy() {
        return new MonsterCard(
                Instant.now().toEpochMilli(),
                this.cardState,
                this.name,
                this.cardType,
                this.description,
                this.attribute,
                this.monsterType,
                this.rank,
                this.attack,
                this.defense);
    }
}
