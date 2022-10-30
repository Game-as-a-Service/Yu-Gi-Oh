package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class SpringMvcVertxJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcVertxJavaApplication.class, args);
    }
}
