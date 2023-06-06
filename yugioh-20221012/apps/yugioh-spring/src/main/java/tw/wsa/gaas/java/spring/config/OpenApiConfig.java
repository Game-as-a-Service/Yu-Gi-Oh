package tw.wsa.gaas.java.spring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Yu-Gi-Oh! API",
                description = "什麼是看得見，卻又看不見的東西呢？"
        ),
        servers = {
                @Server(
                        url = "{schema}://{domain-name}/{context-path}",
                        variables = {
                                @ServerVariable(
                                        name = "schema",
                                        allowableValues = {"http", "https"},
                                        defaultValue = "http"
                                ),
                                @ServerVariable(
                                        name = "domain-name",
                                        allowableValues = {"localhost"},
                                        defaultValue = "localhost"
                                ),
                                @ServerVariable(
                                        name = "context-path",
                                        allowableValues = {"yugioh"},
                                        defaultValue = "yugioh"
                                )
                        }
                ),
        }
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authentication",
        description = "JWT",
        scheme = "Bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
