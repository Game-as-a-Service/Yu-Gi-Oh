package tw.wsa.gaas.java.application.adaptar.outport;

import java.util.List;

public interface Presenter {

    <T> Void present(List<T> events);
}
