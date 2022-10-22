package tw.wsa.yugioh.config;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.wsa.yugioh.web.ws.WsServerVerticle;

@Configuration
public class VertxConfig {

    @Bean
    public Vertx vertx() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(
                WsServerVerticle.class.getName(),
                new DeploymentOptions().setInstances(VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE)
        );
        return vertx;
    }
}
