package tw.wsa.gaas.java.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tw.wsa.gaas.java.application.usecase.DuelFieldCommandUseCase;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;

@EnableScheduling
@Configuration
public class AppConfig {

    @Bean
    DuelFieldCommandUseCase duelFieldUseCase(DuelFieldRepository duelFieldRepository) {
        return new DuelFieldCommandUseCase(duelFieldRepository);
    }
}
