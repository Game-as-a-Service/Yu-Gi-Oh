package tw.gaas.yugioh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class YugiohApplication

fun main(args: Array<String>) {
    runApplication<YugiohApplication>(*args)
}
