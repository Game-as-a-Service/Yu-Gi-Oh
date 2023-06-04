package tw.wsa.gaas.java.domain.repository;

import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;

public interface DuelFieldRepository {

    DuelField selectById(EntityId entityId);
}
