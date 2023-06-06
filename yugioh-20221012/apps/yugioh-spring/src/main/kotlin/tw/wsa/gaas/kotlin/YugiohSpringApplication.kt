package tw.wsa.gaas.kotlin

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication(
    scanBasePackages = ["tw.wsa.gaas.java"]
)
class YugiohSpringApplication {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun commandLineRunner(
        applicationContext: ApplicationContext,
    ): CommandLineRunner =
        CommandLineRunner {
            val applicationName = applicationContext.environment.getRequiredProperty("spring.application.name")
            log.info("http://localhost:8080/$applicationName/swagger-ui/index.html")
        }
}

fun main(args: Array<String>) {
    runApplication<YugiohSpringApplication>(*args)
}
