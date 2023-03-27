package tw.gaas.yugioh.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.gaas.yugioh.application.adapter.outport.DuelFieldPresenter;
import tw.gaas.yugioh.application.repository.DuelFieldRepository;
import tw.gaas.yugioh.application.usecase.req.CardReqDTO;
import tw.gaas.yugioh.domain.DomainEvent;
import tw.gaas.yugioh.domain.DuelField;
import tw.gaas.yugioh.domain.DuelFieldEvent;
import tw.gaas.yugioh.domain.vo.Duelist;
import tw.gaas.yugioh.domain.vo.Zone;

import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DuelFieldUseCase {


    private final DuelFieldRepository duelFieldRepository;

    public void join(
            String duelistName,
            DuelFieldPresenter duelFieldPresenter) {
        duelFieldRepository
                .findByDuelistName(duelistName)
                .map(duelField -> {
                    final DuelFieldEvent duelFieldEvent = duelField.prepareRightZone(new Zone(new Duelist(duelistName)));

                    duelFieldRepository.save(duelField);

                    duelFieldPresenter.present(List.of(duelFieldEvent));

                    return null;
                })
                .orElseGet(() -> {
                    final DuelField duelField = new DuelField(UUID.randomUUID().toString());
                    final DuelFieldEvent duelFieldEvent = duelField.prepareLeftZone(new Zone(new Duelist(duelistName)));

                    duelFieldRepository.save(duelField);

                    duelFieldPresenter.present(List.of(duelFieldEvent));

                    return Optional.empty();
                });
    }

    public Boolean drawCard(
            String uuid,
            String name,
            Boolean skip
    ) {
        return duelFieldRepository
                .findByUuid(uuid) // 查
                .map(duelField -> { // 改
                    final DomainEvent domainEvent = duelField.drawCard(name);
                    return new AbstractMap.SimpleEntry<>(domainEvent, duelField);
                })
                .flatMap(v -> { // 存
                    final DomainEvent event = v.getKey();
                    final DuelField entity = v.getValue();
                    return duelFieldRepository
                            .save(entity)
                            .map(v1 -> event);
                })
                .map(this::publish) // 推
                .orElseThrow(RuntimeException::new);
    }

    public Boolean summonMonster(
            String uuid,
            String name,
            Boolean skip,
            CardReqDTO cardReqDTO
    ) {
        return duelFieldRepository
                .findByUuid(uuid)
                .map(duelField -> {
                    final DomainEvent domainEvent = duelField.summonMonster(name);
                    return new AbstractMap.SimpleEntry<>(domainEvent, duelField);
                })
                .flatMap(v -> {
                    final DomainEvent event = v.getKey();
                    final DuelField entity = v.getValue();
                    return duelFieldRepository
                            .save(entity)
                            .map(v1 -> event);
                })
                .map(this::publish)
                .orElseThrow(RuntimeException::new);
    }

    public Boolean applySpell(
            String uuid,
            String name,
            Boolean skip,
            CardReqDTO cardReqDTO
    ) {
        return duelFieldRepository
                .findByUuid(uuid)
                .map(duelField -> {
                    final DomainEvent domainEvent = duelField.applySpell(name);
                    return new AbstractMap.SimpleEntry<>(domainEvent, duelField);
                })
                .flatMap(v -> {
                    final DomainEvent event = v.getKey();
                    final DuelField entity = v.getValue();
                    return duelFieldRepository
                            .save(entity)
                            .map(v1 -> event);
                })
                .map(this::publish)
                .orElseThrow(RuntimeException::new);
    }

    public Boolean coverTrap(
            String uuid,
            String name,
            Boolean skip,
            CardReqDTO cardReqDTO
    ) {
        return duelFieldRepository
                .findByUuid(uuid)
                .map(duelField -> {
                    final DomainEvent domainEvent = duelField.coverTrap(name);
                    return new AbstractMap.SimpleEntry<>(domainEvent, duelField);
                })
                .flatMap(v -> {
                    final DomainEvent event = v.getKey();
                    final DuelField entity = v.getValue();
                    return duelFieldRepository
                            .save(entity)
                            .map(v1 -> event);
                })
                .map(this::publish)
                .orElseThrow(RuntimeException::new);
    }

    public Boolean startBattle(
            String uuid,
            String name,
            Boolean skip,
            CardReqDTO cardReqDTO
    ) {
        return duelFieldRepository
                .findByUuid(uuid)
                .map(duelField -> {
                    final DomainEvent domainEvent = duelField.startBattle(name);
                    return new AbstractMap.SimpleEntry<>(domainEvent, duelField);
                })
                .flatMap(v -> {
                    final DomainEvent event = v.getKey();
                    final DuelField entity = v.getValue();
                    return duelFieldRepository
                            .save(entity)
                            .map(v1 -> event);
                })
                .map(this::publish)
                .orElseThrow(RuntimeException::new);
    }

    private Boolean publish(DomainEvent event) {
        // duelFieldUuidToSseEmitters
        return Boolean.TRUE;
    }
}
