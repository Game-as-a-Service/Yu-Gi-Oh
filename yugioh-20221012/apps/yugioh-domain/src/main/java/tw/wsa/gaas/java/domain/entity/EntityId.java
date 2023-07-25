package tw.wsa.gaas.java.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
public class EntityId {

    private String uuid;
    private String createdBy;
    private String lastModifiedBy;
    private Long createdDate;
    private Long lastModifiedDate;
    private String memo;
}
