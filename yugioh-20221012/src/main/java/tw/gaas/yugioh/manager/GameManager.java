package tw.gaas.yugioh.manager;

import org.springframework.stereotype.Component;
import tw.gaas.yugioh.data.entity.DuelField;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class GameManager {

    private final ConcurrentLinkedQueue<String> pairPool = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, DuelField> uuidAndDuelField = new ConcurrentHashMap<>();

    public String requestPair() {
        return pairPool.poll();
    }

    public void submitPairRequest(String duelFieldUuid) {
        pairPool.offer(duelFieldUuid);
    }

    public void putDuelFieldWithUuid(String uuid, DuelField duelField) {
        uuidAndDuelField.put(uuid, duelField);
    }

    public DuelField findDuelFieldByUuid(String duelFieldUuid) {
        return uuidAndDuelField.get(duelFieldUuid);
    }

    public ConcurrentHashMap<String, DuelField> allDuelFields() {
        return uuidAndDuelField;
    }
}
