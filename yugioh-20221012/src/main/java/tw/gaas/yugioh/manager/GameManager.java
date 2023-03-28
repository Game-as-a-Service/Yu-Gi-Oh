package tw.gaas.yugioh.manager;

import org.springframework.stereotype.Component;
import tw.gaas.yugioh.data.entity.DuelField;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameManager {

    private final ConcurrentHashMap<String, DuelField> uuidAndDuelField = new ConcurrentHashMap<>();

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
