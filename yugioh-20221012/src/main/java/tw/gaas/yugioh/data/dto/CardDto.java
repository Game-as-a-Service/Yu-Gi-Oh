package tw.gaas.yugioh.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.data.enu.Attribute;
import tw.gaas.yugioh.data.enu.MonsterType;
import tw.gaas.yugioh.data.enu.SpellType;
import tw.gaas.yugioh.data.enu.State;
import tw.gaas.yugioh.data.enu.TrapType;
import tw.gaas.yugioh.data.enu.Type;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private String uuid;

    private State state;

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
