package tw.wsa.gaas.java.application.usecase;

import lombok.RequiredArgsConstructor;
import tw.wsa.gaas.java.application.adapter.inport.DuelFieldCommand;
import tw.wsa.gaas.java.application.adapter.outport.EventBus;
import tw.wsa.gaas.java.application.adapter.outport.Presenter;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;
import tw.wsa.gaas.java.domain.vo.Duelist;
import tw.wsa.gaas.java.domain.vo.Zone;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DuelFieldCommandUseCase {

    private final EventBus eventBus;

    private final DuelFieldRepository duelFieldRepository;

//    public void execute(DuelFieldQuery query, Presenter presenter) {
//        final Optional<DuelField> duelFieldOpt = duelFieldRepository.selectById(EntityId.builder().uuid(query.getUuid()).build());
//
//        duelFieldOpt.map(duelField -> presenter.present(List.of(duelField)));
//    }
//
//    public List<DuelField> fetchAll() {
//        return duelFieldRepository.selectAll();
//    }

    public void execute(DuelFieldCommand command, Presenter presenter) {
        switch (command.getCommandType()) {
            case JOIN:
                duelFieldRepository
                        .selectWaiting()
                        // 存在等待中的對戰場地，加入
                        .map(duelField -> {
                            final DuelFieldEvent duelFieldEvent = duelField.prepareRightZone(new Zone(new Duelist(command.getDuelistName())));

                            duelFieldRepository.save(duelField);

                            eventBus.broadcast(List.of(duelField));
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
                duelFieldRepository
                        // 查
                        .selectById(EntityId.builder().uuid(command.getUuid()).build())
                        .map(duelField -> {
                            // 改
                            final DuelFieldEvent duelFieldEvent = duelField.drawCard(command.getDuelistName());

                            // 存
                            duelFieldRepository.save(duelField);

                            return Pair.of(duelField, duelFieldEvent);
                        })
                        .flatMap(pair -> {
                            // 推
                            eventBus.broadcast(List.of(pair.getKey()));
                            return presenter.present(List.of(pair.getValue()));
                        });
                break;
            case SUMMON_MONSTER:
                duelFieldRepository
                        // 查
                        .selectById(EntityId.builder().uuid(command.getUuid()).build())
                        .map(duelField -> {
                            // 改
                            final DuelFieldEvent duelFieldEvent = duelField.summonMonster(
                                    command.getSkip(),
                                    command.getCardUuid(),
                                    command.getCardState(),
                                    command.getDuelistName()
                            );

                            // 存
                            duelFieldRepository.save(duelField);

                            return Pair.of(duelField, duelFieldEvent);
                        })
                        .flatMap(pair -> {
                            // 推
                            eventBus.broadcast(List.of(pair.getKey()));
                            return presenter.present(List.of(pair.getValue()));
                        });
                break;
            case APPLY_SPELL:
                duelFieldRepository
                        // 查
                        .selectById(EntityId.builder().uuid(command.getUuid()).build())
                        .map(duelField -> {
                            // 改
                            final DuelFieldEvent duelFieldEvent = duelField.applySpell(
                                    command.getSkip(),
                                    command.getCardUuid(),
                                    command.getDuelistName()
                            );

                            // 存
                            duelFieldRepository.save(duelField);

                            return Pair.of(duelField, duelFieldEvent);
                        })
                        .flatMap(pair -> {
                            // 推
                            eventBus.broadcast(List.of(pair.getKey()));
                            return presenter.present(List.of(pair.getValue()));
                        });
                break;
            case COVER_TRAP:
                duelFieldRepository
                        // 查
                        .selectById(EntityId.builder().uuid(command.getUuid()).build())
                        .map(duelField -> {
                            // 改
                            final DuelFieldEvent duelFieldEvent = duelField.coverTrap(
                                    command.getSkip(),
                                    command.getCardUuid(),
                                    command.getCardState(),
                                    command.getDuelistName()
                            );

                            // 存
                            duelFieldRepository.save(duelField);

                            return Pair.of(duelField, duelFieldEvent);
                        })
                        .flatMap(pair -> {
                            // 推
                            eventBus.broadcast(List.of(pair.getKey()));
                            return presenter.present(List.of(pair.getValue()));
                        });
                break;
            case START_BATTLE:
                duelFieldRepository
                        // 查
                        .selectById(EntityId.builder().uuid(command.getUuid()).build())
                        .map(duelField -> {
                            // 改
                            final DuelFieldEvent duelFieldEvent = duelField.startBattle(
                                    command.getSkip(),
                                    command.getCardUuid(),
                                    command.getDuelistName()
                            );

                            // 存
                            duelFieldRepository.save(duelField);

                            return Pair.of(duelField, duelFieldEvent);
                        })
                        .flatMap(pair -> {
                            // 推
                            eventBus.broadcast(List.of(pair.getKey()));
                            return presenter.present(List.of(pair.getValue()));
                        });
                break;
        }
    }

    private static class Pair {

        public static <T, U> Map.Entry<T, U> of(T first, U second) {
            return new AbstractMap.SimpleEntry<>(first, second);
        }
    }
}
