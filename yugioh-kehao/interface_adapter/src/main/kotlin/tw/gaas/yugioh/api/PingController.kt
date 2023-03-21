package tw.gaas.yugioh.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    @RequestMapping(value = ["/api/ping"], produces = ["text/plain"], method = [RequestMethod.GET])
    fun pong(): ResponseEntity<String> {
        return ResponseEntity
            .ok()
            .body("pong")
    }
}
