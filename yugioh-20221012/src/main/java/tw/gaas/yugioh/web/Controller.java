package tw.gaas.yugioh.web;

import net.purefunc.emoji.Emoji2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/java/api/v1.0")
public class Controller {

    @GetMapping("/status")
    String getStatus() {
        return String.format("Hello Duels! %s", Emoji2.MILITARY_MEDAL);
    }
}
