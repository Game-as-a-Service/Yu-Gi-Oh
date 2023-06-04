package tw.wsa.gaas.java.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityId {

    String uuid;
    String createdBy;
    String lastModifiedBy;
    Long createdDate;
    Long lastModifiedDate;
    String memo;
}
