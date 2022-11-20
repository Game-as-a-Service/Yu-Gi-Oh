package tw.gaas.yugioh.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.domain.card.enu.Attribute;
import tw.gaas.yugioh.domain.card.enu.MonsterType;
import tw.gaas.yugioh.domain.card.enu.SpellType;
import tw.gaas.yugioh.domain.card.enu.TrapType;
import tw.gaas.yugioh.domain.card.enu.Type;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private String name;

    private Type type;

    private String description;

    private Attribute attribute;

    private MonsterType monsterType;

    private SpellType spellType;

    private TrapType trapType;

    private Integer rank;

    private Integer attack;

    private Integer defense;
}
