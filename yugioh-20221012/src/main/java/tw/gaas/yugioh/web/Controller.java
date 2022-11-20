package tw.gaas.yugioh.web;

import lombok.extern.slf4j.Slf4j;
import net.purefunc.emoji.Emoji2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tw.gaas.yugioh.manager.GameManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
public class Controller {

    private final GameManager gameManager;

    public Controller(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/status")
    String getStatus() {
        return String.format("Hello Duels! %s", Emoji2.MILITARY_MEDAL);
    }

    @PostMapping("/games/{username}")
    String joinGames(@PathVariable String username) {
        final String pairGameUuid = gameManager.requestPair();

        if (pairGameUuid == null) {
            final String uuid = UUID.randomUUID().toString();
            return gameManager.enterLeft(uuid);
        } else {
            return gameManager.enterRight(pairGameUuid);
        }
    }

    @GetMapping("/games/{gameUuid}")
    SseEmitter registerDuelFieldsListener(@PathVariable String gameUuid) throws IOException {
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.send(
                SseEmitter
                        .event()
                        .id(LocalDateTime.now().toString())
                        .name("Connected, you're listening " + gameUuid + " duel field.")
                        .reconnectTime(1000)
        );

        return gameManager.register(gameUuid, sseEmitter);
    }
}
