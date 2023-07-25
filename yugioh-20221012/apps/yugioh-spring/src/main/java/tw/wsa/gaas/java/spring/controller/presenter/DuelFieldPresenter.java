package tw.wsa.gaas.java.spring.controller.presenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import tw.wsa.gaas.java.application.adapter.outport.Presenter;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;

import java.util.List;
import java.util.Optional;

@Slf4j
public class DuelFieldPresenter implements Presenter {

    private Optional<String> responseOpt;

    @Override
    public <T> Optional<Void> present(List<T> events) {
        events.forEach(v -> {
            if (v instanceof DuelFieldEvent) {
                DuelFieldEvent event = (DuelFieldEvent) v;
                responseOpt = Optional.of(event.getEntityId().getUuid());
            } else {
                log.warn("Unknown event: {}", v);
            }
        });

        return Optional.empty();
    }

    public ResponseEntity<String> returnUuidResp() {
        return responseOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> returnAccepted() {
        return responseOpt
                .map(v -> ResponseEntity.accepted().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
