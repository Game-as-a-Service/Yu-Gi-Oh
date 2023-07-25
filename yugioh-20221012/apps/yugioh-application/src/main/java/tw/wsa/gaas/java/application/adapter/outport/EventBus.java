package tw.wsa.gaas.java.application.adapter.outport;

import java.util.List;

public interface EventBus {

    <T> void broadcast(List<T> event);
}
