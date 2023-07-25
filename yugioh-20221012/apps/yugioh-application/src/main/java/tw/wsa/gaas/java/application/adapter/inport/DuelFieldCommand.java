package tw.wsa.gaas.java.application.adapter.inport;

import lombok.Builder;
import lombok.Getter;
import tw.wsa.gaas.java.domain.enu.CardState;
import tw.wsa.gaas.java.domain.enu.CommandType;

@Builder(toBuilder = true)
@Getter
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
