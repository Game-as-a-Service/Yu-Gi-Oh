package tw.wsa.gaas.java.application.usecase.query;

import lombok.RequiredArgsConstructor;
import tw.wsa.gaas.java.application.adaptar.outport.Presenter;
import tw.wsa.gaas.java.application.adaptar.inport.query.DuelFieldQuery;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.entity.EntityId;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;

import java.util.List;

@RequiredArgsConstructor
public class DuelFieldQueryUseCase {

    private final DuelFieldRepository duelFieldRepository;

    public void queryDuelFieldByUuid(
            DuelFieldQuery query,
            Presenter presenter
    ) {
        final DuelField duelField = duelFieldRepository.selectById(EntityId.builder().uuid(query.getUuid()).build());

        presenter.present(List.of(duelField));
    }
}
