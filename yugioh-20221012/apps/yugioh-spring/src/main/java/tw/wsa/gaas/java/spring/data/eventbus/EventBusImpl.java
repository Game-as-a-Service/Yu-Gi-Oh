package tw.wsa.gaas.java.spring.data.eventbus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.wsa.gaas.java.application.adapter.outport.EventBus;
import tw.wsa.gaas.java.domain.entity.DuelField;
import tw.wsa.gaas.java.spring.controller.SseController;
import tw.wsa.gaas.java.spring.controller.view.DuelFieldView;

import java.io.IOException;
import java.time.OffsetDateTime;
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
                                            final DuelFieldView duelFieldView = DuelFieldView
                                                    .builder()
                                                    .uuid(duelField.getEntityId().getUuid())
                                                    .left(duelField.getLeft())
                                                    .right(duelField.getRight())
                                                    .phase(duelField.getPhase())
                                                    .build();

                                            sseEmitter.send(
                                                    SseEmitter
                                                            .event()
                                                            .id(OffsetDateTime.now().toString())
                                                            .name(String.format("DuelField:%s", duelField.getEntityId().getUuid()))
                                                            .data(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(duelFieldView))
                                            );
                                        } catch (IOException ex) {
                                            sseEmitter.completeWithError(ex);
                                        }
                                    });
                        }
                )
        );
    }
}
