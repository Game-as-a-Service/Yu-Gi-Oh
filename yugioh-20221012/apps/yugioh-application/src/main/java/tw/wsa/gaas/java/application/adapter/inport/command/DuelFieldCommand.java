package tw.wsa.gaas.java.application.adapter.inport.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CommandType;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldCommand {

    private CommandType commandType;
    private String uuid;
    private String duelistName;
    private Boolean skip;
    private String cardUuid;
    private CardState cardState;

    public static DuelFieldCommandBuilder join() {
        return DuelFieldCommand.builder().commandType(CommandType.JOIN);
    }

    public static DuelFieldCommandBuilder drawCard() {
        return DuelFieldCommand.builder().commandType(CommandType.DRAW_CARD);
    }

    public static DuelFieldCommandBuilder summonMonster() {
        return DuelFieldCommand.builder().commandType(CommandType.SUMMON_MONSTER);
    }

    public static DuelFieldCommandBuilder applySpell() {
        return DuelFieldCommand.builder().commandType(CommandType.APPLY_SPELL);
    }

    public static DuelFieldCommandBuilder coverTrap() {
        return DuelFieldCommand.builder().commandType(CommandType.COVER_TRAP);
    }

    public static DuelFieldCommandBuilder startBattle() {
        return DuelFieldCommand.builder().commandType(CommandType.START_BATTLE);
    }
}
