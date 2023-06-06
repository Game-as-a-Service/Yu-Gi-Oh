package tw.wsa.gaas.java.spring.data;

import org.springframework.stereotype.Repository;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class DuelFieldRepositoryImpl implements DuelFieldRepository {

    private final ConcurrentLinkedQueue<DuelField> pairPool = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, DuelField> uuidAndDuelField = new ConcurrentHashMap<>();

    @Override
    public Optional<DuelField> selectById(EntityId entityId) {
        return Optional.ofNullable(uuidAndDuelField.get(entityId.getUuid()));
    }

    @Override
    public List<DuelField> selectAll() {
        return new ArrayList<>(uuidAndDuelField.values());
    }

    @Override
    public Optional<DuelField> selectWaiting() {
        return Optional.ofNullable(pairPool.poll());
    }

    @Override
    public Optional<DuelField> insert(DuelField duelField) {
        pairPool.offer(duelField);
        return Optional.of(duelField);
    }

    @Override
    public Optional<DuelField> save(DuelField duelField) {
        return uuidAndDuelField.containsKey(duelField.getEntityId().getUuid())
                ? Optional.of(duelField)
                : Optional.ofNullable(uuidAndDuelField.put(duelField.getEntityId().getUuid(), duelField));
    }
}
