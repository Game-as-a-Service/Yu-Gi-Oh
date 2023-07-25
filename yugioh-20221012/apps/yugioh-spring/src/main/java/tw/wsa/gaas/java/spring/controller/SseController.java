package tw.wsa.gaas.java.spring.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseController {

    private final ConcurrentHashMap<String, List<SseEmitter>> duelFieldUuidAndSseEmitters = new ConcurrentHashMap<>();

    public void put(String uuid, SseEmitter sseEmitter) {
        if (duelFieldUuidAndSseEmitters.containsKey(uuid)) {
            duelFieldUuidAndSseEmitters.get(uuid).add(sseEmitter);
        } else {
            duelFieldUuidAndSseEmitters.put(uuid, List.of(sseEmitter));
        }
    }

    public List<SseEmitter> get(String uuid) {
        return Optional
                .ofNullable(duelFieldUuidAndSseEmitters.get(uuid))
                .orElse(List.of());
    }
}
