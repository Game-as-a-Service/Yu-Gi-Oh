package tw.wsa.gaas.java.spring.controller.presenter;

import org.springframework.http.ResponseEntity;
import tw.wsa.gaas.java.application.adaptar.outport.Presenter;
import tw.wsa.gaas.java.spring.controller.view.DuelFieldView;

import java.util.List;
import java.util.Optional;

public class DuelFieldPresenter implements Presenter {

    private Optional<DuelFieldView> viewModelOpt;

    @Override
    public <T> Void present(List<T> events) {
        return null;
    }

    public ResponseEntity<DuelFieldView> retrieveDuelField() {
        return viewModelOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
