package tw.wsa.gaas.java.domain.event;

public class DuelFieldEvent implements DomainEvent {

    private final String duelFieldUuid;

    public DuelFieldEvent(String duelFieldUuid) {
        this.duelFieldUuid = duelFieldUuid;
    }
}
