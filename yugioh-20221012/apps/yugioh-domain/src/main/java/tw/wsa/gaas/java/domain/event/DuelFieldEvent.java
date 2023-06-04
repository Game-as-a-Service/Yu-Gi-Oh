package tw.wsa.gaas.java.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.wsa.gaas.java.domain.entity.EntityId;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuelFieldEvent implements DomainEvent {

    EntityId entityId;
}
