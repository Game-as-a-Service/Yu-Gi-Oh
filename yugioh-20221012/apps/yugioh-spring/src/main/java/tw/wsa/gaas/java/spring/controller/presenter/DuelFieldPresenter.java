package tw.wsa.gaas.java.spring.controller.presenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import tw.wsa.gaas.java.application.adapter.outport.Presenter;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class DuelFieldPresenter implements Presenter {

    private Optional<Map<String, Object>> responseMapOpt;

    @Override
    public <T> Optional<Void> present(List<T> events) {
        events.forEach(v -> {
            if (v instanceof DuelFieldEvent) {
                DuelFieldEvent event = (DuelFieldEvent) v;
                responseMapOpt = Optional.of(Map.of("uuid", event.getEntityId().getUuid()));
            } else {
                log.warn("Unknown event: {}", v);
            }
        });

        return Optional.empty();
    }

    public ResponseEntity<Map<String, Object>> returnViewResp() {
        return responseMapOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> returnAccepted() {
        return responseMapOpt
                .map(v -> ResponseEntity.accepted().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
