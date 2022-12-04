package tw.gaas.yugioh.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ping")
class PingController {
    @GetMapping("/")
    fun pong(): ResponseEntity<String> {
        return ResponseEntity
            .ok()
            .body("pong")
    }
}
