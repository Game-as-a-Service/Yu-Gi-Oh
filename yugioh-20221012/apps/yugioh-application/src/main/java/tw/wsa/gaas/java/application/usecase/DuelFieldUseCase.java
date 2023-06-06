package tw.wsa.gaas.java.application.usecase;

import lombok.RequiredArgsConstructor;
import tw.wsa.gaas.java.application.adapter.inport.command.DuelFieldCommand;
import tw.wsa.gaas.java.application.adapter.inport.query.DuelFieldQuery;
import tw.wsa.gaas.java.application.adapter.outport.Presenter;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;
import tw.wsa.gaas.java.domain.event.DomainEvent;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;
import tw.wsa.gaas.java.domain.vo.Duelist;
import tw.wsa.gaas.java.domain.vo.Zone;

import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DuelFieldUseCase {

    private final DuelFieldRepository duelFieldRepository;

    public void execute(DuelFieldQuery query, Presenter presenter) {
        final Optional<DuelField> duelFieldOpt = duelFieldRepository.selectById(EntityId.builder().uuid(query.getUuid()).build());

        duelFieldOpt.map(duelField -> presenter.present(List.of(duelField)));
    }

    public List<DuelField> fetchAll() {
        return duelFieldRepository.selectAll();
    }

    public void execute(DuelFieldCommand command, Presenter presenter) {
        // 查
        final Optional<DuelField> duelFieldOpt = duelFieldRepository.selectById(EntityId.builder().uuid(command.getUuid()).build());

        switch (command.getCommandType()) {
            case JOIN:
                duelFieldRepository
                        .selectWaiting()
                        // 存在等待中的對戰場地，加入
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.prepareRightZone(new Zone(new Duelist(command.getDuelistName())));

                            duelFieldRepository.save(duelField);

                            return presenter.present(List.of(duelFieldEvent));
                        })
                        // 不存在等待中的對戰場地，創建
                        .orElseGet(() -> {
                            final DuelField duelField = DuelField.create(command.getDuelistName());
                            final DuelFieldEvent duelFieldEvent = duelField.prepareLeftZone(new Zone(new Duelist(command.getDuelistName())));

                            duelFieldRepository.insert(duelField);

                            return presenter.present(List.of(duelFieldEvent));
                        });
                break;
            case DRAW_CARD:
                duelFieldOpt
                        // 改
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.drawCard(command.getDuelistName());

                            return new AbstractMap.SimpleEntry<>(duelFieldEvent, duelField);
                        })
                        // 存
                        .flatMap(eventAndDomain -> duelFieldRepository.save(eventAndDomain.getValue()).map(v -> (DomainEvent) eventAndDomain.getKey()))
                        // 推
                        .map(event -> presenter.present(List.of(event)));
                break;
            case SUMMON_MONSTER:
                duelFieldOpt
                        // 改
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.summonMonster(
                                    false,
                                    command.getCardUuid(),
                                    command.getCardState(),
                                    command.getDuelistName()
                            );

                            return new AbstractMap.SimpleEntry<>(duelFieldEvent, duelField);
                        })
                        // 存
                        .flatMap(eventAndDomain -> duelFieldRepository.save(eventAndDomain.getValue()).map(v -> (DomainEvent) eventAndDomain.getKey()))
                        // 推
                        .map(event -> presenter.present(List.of(event)));
                break;
            case APPLY_SPELL:
                duelFieldOpt
                        // 改
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.applySpell(
                                    false,
                                    command.getCardUuid(),
                                    command.getDuelistName()
                            );

                            return new AbstractMap.SimpleEntry<>(duelFieldEvent, duelField);
                        })
                        // 存
                        .flatMap(eventAndDomain -> duelFieldRepository.save(eventAndDomain.getValue()).map(v -> (DomainEvent) eventAndDomain.getKey()))
                        // 推
                        .map(event -> presenter.present(List.of(event)));
                break;
            case COVER_TRAP:
                duelFieldOpt
                        // 改
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.coverTrap(
                                    false,
                                    command.getCardUuid(),
                                    command.getCardState(),
                                    command.getDuelistName()
                            );

                            return new AbstractMap.SimpleEntry<>(duelFieldEvent, duelField);
                        })
                        // 存
                        .flatMap(eventAndDomain -> duelFieldRepository.save(eventAndDomain.getValue()).map(v -> (DomainEvent) eventAndDomain.getKey()))
                        // 推
                        .map(event -> presenter.present(List.of(event)));
                break;
            case START_BATTLE:
                duelFieldOpt
                        // 改
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.startBattle(
                                    false,
                                    command.getCardUuid(),
                                    command.getDuelistName()
                            );

                            return new AbstractMap.SimpleEntry<>(duelFieldEvent, duelField);
                        })
                        // 存
                        .flatMap(eventAndDomain -> duelFieldRepository.save(eventAndDomain.getValue()).map(v -> (DomainEvent) eventAndDomain.getKey()))
                        // 推
                        .map(event -> presenter.present(List.of(event)));
                break;
        }
    }
}