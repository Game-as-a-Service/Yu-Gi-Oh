package com.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "core")
@Configuration
@Getter
@Setter
public class CoreConfig {

    private String domain;

    private Integer port;
}
