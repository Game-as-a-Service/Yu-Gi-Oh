package tw.wsa.gaas.java.domain.event;

import lombok.*;
import tw.wsa.gaas.java.domain.entity.EntityId;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class DuelFieldEvent implements DomainEvent {

    private EntityId entityId;
}
