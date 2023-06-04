package tw.wsa.gaas.java.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tw.wsa.gaas.java.application.usecase.DuelFieldUseCase;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;

@EnableScheduling
@Configuration
public class AppConfig {

    @Bean
    DuelFieldUseCase duelFieldUseCase(DuelFieldRepository duelFieldRepository) {
        return new DuelFieldUseCase(duelFieldRepository);
    }
}
