package tw.gaas.yugioh.application.repository;

import tw.gaas.yugioh.domain.DuelField;

import java.util.Optional;

public interface DuelFieldRepository {

    Optional<DuelField> findByDuelistName(String duelListName);

    Optional<DuelField> findByUuid(String uuid);

    Optional<DuelField> save(DuelField duelField);
}
