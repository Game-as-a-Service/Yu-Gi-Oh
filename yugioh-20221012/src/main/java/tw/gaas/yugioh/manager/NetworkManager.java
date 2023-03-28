package tw.gaas.yugioh.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class NetworkManager {

    private final GameManager gameManager;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, List<SseEmitter>> duelFieldUuidAndSseEmitters = new ConcurrentHashMap<>();

    public NetworkManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public SseEmitter register(String duelFieldUuid, SseEmitter sseEmitter) {
        if (duelFieldUuidAndSseEmitters.containsKey(duelFieldUuid)) {
            duelFieldUuidAndSseEmitters.get(duelFieldUuid).add(sseEmitter);
        } else {
            duelFieldUuidAndSseEmitters.put(duelFieldUuid, new ArrayList<>() {{
                add(sseEmitter);
            }});
        }

        return sseEmitter;
    }

    @Scheduled(cron = "*/5 * * * * *")
    void broadcastDuelFieldsTask() {
        gameManager
                .allDuelFields()
                .forEach((uuid, duelField) ->
                        Optional
                                .ofNullable(duelFieldUuidAndSseEmitters.get(uuid))
                                .ifPresent(emitters -> emitters
                                        .forEach(emitter -> {
                                            try {
                                                emitter.send(
                                                        SseEmitter
                                                                .event()
                                                                .id(OffsetDateTime.now().toString())
                                                                .name(String.format("Game:%s", uuid))
                                                                .data(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(duelField.toDto(null, null)))
                                                );
                                            } catch (IOException ex) {
                                                log.error(ex.getMessage(), ex);
                                            }
                                        })
                                )
                );
    }
}
