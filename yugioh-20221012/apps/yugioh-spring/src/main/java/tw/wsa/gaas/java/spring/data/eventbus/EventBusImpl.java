package tw.wsa.gaas.java.spring.data.eventbus;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tw.wsa.gaas.java.application.adapter.outport.EventBus;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.domain.event.DuelFieldEvent;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;
import tw.wsa.gaas.java.spring.controller.SseController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class EventBusImpl implements EventBus {

    private final SseController sseController;

    private final ExecutorService executorService = new ThreadPoolExecutor(
            10,
            10,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10));

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> void broadcast(List<T> event) {
        executorService.submit(
                () -> event.forEach(v -> {
                            DuelField duelField = (DuelField) v;
                            sseController
                                    .get(duelField.getEntityId().getUuid())
                                    .forEach(sseEmitter -> {
                                        try {
                                            sseEmitter.send(objectMapper.writeValueAsString(duelField));
                                        } catch (Exception ex) {
                                            sseEmitter.completeWithError(ex);
                                        }
                                    });
                        }
                )
        );
    }
}
