package tw.gaas.yugioh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YugiohApplication

fun main(args: Array<String>) {
    runApplication<YugiohApplication>(*args)
}
