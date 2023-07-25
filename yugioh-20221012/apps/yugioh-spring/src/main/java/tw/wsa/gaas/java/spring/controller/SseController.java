package tw.wsa.gaas.java.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseController {

    private final ConcurrentHashMap<String, List<SseEmitter>> duelFieldUuidAndSseEmitters = new ConcurrentHashMap<>();

    public void put(String uuid, SseEmitter sseEmitter) {
        if (duelFieldUuidAndSseEmitters.containsKey(uuid)) {
            duelFieldUuidAndSseEmitters.get(uuid).add(sseEmitter);
        } else {
            final ArrayList<SseEmitter> sseEmitters = new ArrayList<>();
            sseEmitters.add(sseEmitter);
            duelFieldUuidAndSseEmitters.put(uuid, sseEmitters);
        }
    }

    public List<SseEmitter> get(String uuid) {
        return Optional
                .ofNullable(duelFieldUuidAndSseEmitters.get(uuid))
                .orElse(List.of());
    }
}
