package tw.wsa.gaas.java.application.usecase.command;

import lombok.RequiredArgsConstructor;
import tw.wsa.gaas.java.application.adaptar.inport.command.*;
import tw.wsa.gaas.java.application.adaptar.outport.Presenter;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;

@RequiredArgsConstructor
public class DuelFieldCommandUseCase {

    private final DuelFieldRepository duelFieldRepository;

    public void joinDuelField(
            JoinDuelFieldCommand command,
            Presenter presenter
    ) {

    }

    public void drawCard(
            DuelFieldDrawCardCommand command,
            Presenter presenter
    ) {
        final DuelField duelField = duelFieldRepository.selectById(EntityId.builder().uuid(command.getUuid()).build());

    }

    public void summonMonster(
            DuelFieldSummonMonsterCommand command,
            Presenter presenter
    ) {
        final DuelField duelField = duelFieldRepository.selectById(EntityId.builder().uuid(command.getUuid()).build());

    }

    public void applySpell(
            DuelFieldApplySpellCommand command,
            Presenter presenter
    ) {
        final DuelField duelField = duelFieldRepository.selectById(EntityId.builder().uuid(command.getUuid()).build());

    }

    public void coverTrap(
            DuelFieldCoverTrapCommand command,
            Presenter presenter
    ) {
        final DuelField duelField = duelFieldRepository.selectById(EntityId.builder().uuid(command.getUuid()).build());

    }

    public void startBattle(
            DuelFieldStartBattleCommand command,
            Presenter presenter
    ) {
        final DuelField duelField = duelFieldRepository.selectById(EntityId.builder().uuid(command.getUuid()).build());

    }
}
