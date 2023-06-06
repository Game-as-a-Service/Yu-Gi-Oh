package tw.wsa.gaas.java.domain.repository;

import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;

import java.util.List;
import java.util.Optional;

public interface DuelFieldRepository {

    Optional<DuelField> selectById(EntityId entityId);

    List<DuelField> selectAll();

    Optional<DuelField> selectWaiting();

    Optional<DuelField> save(DuelField duelField);

    Optional<DuelField> insert(DuelField duelField);
}
