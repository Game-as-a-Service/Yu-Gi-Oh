package tw.gaas.yugioh.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerDocumentationConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Yu-Gi-Oh")
                    .description("個人練習撰寫 OpenAPI Specification 文件 - [GitHubGame-as-a-Service/Yu-Gi-Oh](https://github.com/Game-as-a-Service/Yu-Gi-Oh)")
                    .termsOfService("")
                    .version("1.0")
                    .license(
                        License()
                            .name("")
                            .url("http://unlicense.org")
                    )
                    .contact(
                        Contact()
                            .email("kehao.chen@happyhacking.ninja")
                    )
            )
    }
}
