package tw.wsa.gaas.java.spring.controller.presenter;

import org.springframework.http.ResponseEntity;
import tw.wsa.gaas.java.application.adapter.outport.Presenter;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;
import tw.wsa.gaas.java.spring.controller.view.DuelFieldView;

import java.util.List;
import java.util.Optional;

public class DuelFieldPresenter implements Presenter {

    private Optional<DuelFieldView> viewModelOpt;

    @Override
    public <T> Optional<Void> present(List<T> events) {
        events.forEach(v -> {
            if (v instanceof DuelField) {
                DuelField entity = (DuelField) v;
                viewModelOpt = Optional.of(new DuelFieldView(entity.getEntityId().getUuid()));
            } else {
                DuelFieldEvent event = (DuelFieldEvent) v;
                viewModelOpt = Optional.of(new DuelFieldView(event.getEntityId().getUuid()));
            }
        });

        return Optional.empty();
    }

    public ResponseEntity<DuelFieldView> returnViewResp() {
        return viewModelOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> returnAccepted() {
        return viewModelOpt
                .map(v -> ResponseEntity.accepted().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
