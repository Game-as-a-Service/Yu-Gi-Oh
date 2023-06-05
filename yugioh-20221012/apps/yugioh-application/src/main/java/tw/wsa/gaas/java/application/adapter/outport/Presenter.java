package tw.wsa.gaas.java.application.adapter.outport;

import java.util.List;
import java.util.Optional;

public interface Presenter {

    <T> Optional<Void> present(List<T> events);
}
