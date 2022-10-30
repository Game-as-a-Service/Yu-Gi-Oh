package com.example.config;

import com.example.web.ws.WsServerVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    @Bean
    Vertx vertx() {
        Vertx vertx = Vertx.vertx();
        vertx
                .deployVerticle(
                        WsServerVerticle.class.getName(),
                        new DeploymentOptions().setInstances(VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE)
                );
        return vertx;
    }
}
