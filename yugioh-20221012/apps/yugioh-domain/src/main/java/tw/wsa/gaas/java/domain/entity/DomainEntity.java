package tw.wsa.gaas.java.domain.entity;

import lombok.*;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainEntity {

    EntityId entityId;
}
