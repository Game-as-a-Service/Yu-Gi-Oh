package tw.gaas.yugioh.application.adapter.outport;

import java.util.List;

public interface DuelFieldPresenter {

    <T> void present(List<T> events);

    void presentExec();
}
