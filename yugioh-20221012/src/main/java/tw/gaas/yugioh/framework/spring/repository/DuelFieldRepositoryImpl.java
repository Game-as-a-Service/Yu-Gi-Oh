package tw.gaas.yugioh.framework.spring.repository;

import org.springframework.stereotype.Repository;
import tw.gaas.yugioh.domain.DuelField;
import tw.gaas.yugioh.application.repository.DuelFieldRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class DuelFieldRepositoryImpl implements DuelFieldRepository {

    private final ConcurrentLinkedQueue<String> pairPool = new ConcurrentLinkedQueue<>();

    private final ConcurrentHashMap<String, DuelField> uuidToDuelField = new ConcurrentHashMap<>();

    @Override
    public Optional<DuelField> findByDuelistName(String duelListName) {

        pairPool.offer(UUID.randomUUID().toString());
        return Optional.empty();
    }

    @Override
    public Optional<DuelField> findByUuid(String uuid) {
        return Optional.of(uuidToDuelField.get(uuid));
    }

    @Override
    public Optional<DuelField> save(DuelField duelField) {
        return Optional.empty();
    }
}
