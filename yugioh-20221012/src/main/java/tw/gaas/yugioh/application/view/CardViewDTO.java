package tw.gaas.yugioh.application.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.gaas.yugioh.domain.enu.Attribute;
import tw.gaas.yugioh.domain.enu.MonsterType;
import tw.gaas.yugioh.domain.enu.SpellType;
import tw.gaas.yugioh.domain.enu.State;
import tw.gaas.yugioh.domain.enu.TrapType;
import tw.gaas.yugioh.domain.enu.Type;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardViewDTO {

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
