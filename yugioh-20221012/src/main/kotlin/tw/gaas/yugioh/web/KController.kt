package tw.gaas.yugioh.web

import net.purefunc.emoji.Emoji2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin/api/v1.0")
class KController {

    @GetMapping("/status")
    fun getStatus() = "Hello KDuelist! ${Emoji2.MILITARY_MEDAL}${Emoji2.MILITARY_MEDAL}${Emoji2.MILITARY_MEDAL}"
}