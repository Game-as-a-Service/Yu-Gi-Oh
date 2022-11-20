package tw.gaas.yugioh.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.gaas.yugioh.domain.field.DuelField;
import tw.gaas.yugioh.domain.field.Zone;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
public class GameManager {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentLinkedQueue<String> pairPool = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, DuelField> gameUuidAndDuelField = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<SseEmitter>> gameUuidAndSseEmitters = new ConcurrentHashMap<>();

    public String requestPair() {
        return pairPool.poll();
    }

    public String enterLeft(String gameUuid, DuelField duelField) {
        gameUuidAndDuelField.put(gameUuid, duelField);
        pairPool.offer(gameUuid);
        return "curl -H \"Accept:text/event-stream\" http://localhost:8080/java/api/v1.0/games/" + gameUuid;
    }

    public String enterRight(String pairGameUuid, Zone zone) {
        final DuelField duelField = gameUuidAndDuelField.get(pairGameUuid);
        duelField.setRight(zone);
        duelField.start();
        return "curl -H \"Accept:text/event-stream\" http://localhost:8080/java/api/v1.0/games/" + pairGameUuid;
    }

    public SseEmitter register(String gameUuid, SseEmitter sseEmitter) {
        if (gameUuidAndSseEmitters.containsKey(gameUuid)) {
            gameUuidAndSseEmitters.get(gameUuid).add(sseEmitter);
        } else {
            gameUuidAndSseEmitters.put(gameUuid, List.of(sseEmitter));
        }

        return sseEmitter;
    }

    @Scheduled(cron = "*/5 * * * * *")
    void broadcastDuelFieldsTask() {
        gameUuidAndDuelField
                .forEach((gameUuid, duelField) ->
                        Optional
                                .ofNullable(gameUuidAndSseEmitters.get(gameUuid))
                                .ifPresent(emitters -> {
                                    try {
                                        log.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(duelField.toDto()));
                                    } catch (JsonProcessingException ex) {
                                        log.error(ex.getMessage(), ex);
                                    }

                                    emitters.forEach(e -> {
                                        try {
                                            e.send(
                                                    SseEmitter
                                                            .event()
                                                            .id(OffsetDateTime.now().toString())
                                                            .name(String.format("Game:%s", gameUuid))
                                                            .data(objectMapper.writeValueAsString(duelField.toDto()))
                                            );
                                        } catch (IOException ex) {
                                            log.error(ex.getMessage(), ex);
                                        }
                                    });
                                }));
    }
}
