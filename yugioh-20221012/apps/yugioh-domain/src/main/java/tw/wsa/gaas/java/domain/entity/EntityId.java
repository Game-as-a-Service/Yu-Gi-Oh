package tw.wsa.gaas.java.domain.entity;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class EntityId {

    String uuid;
    String createdBy;
    String lastModifiedBy;
    Long createdDate;
    Long lastModifiedDate;
    String memo;
}
