package tw.wsa.gaas.java.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tw.wsa.gaas.java.application.adapter.outport.EventBus;
import tw.wsa.gaas.java.application.usecase.DuelFieldCommandUseCase;
import tw.wsa.gaas.java.domain.repository.DuelFieldRepository;

@EnableScheduling
@Configuration
public class AppConfig {

    @Bean
    public DuelFieldCommandUseCase duelFieldUseCase(
            EventBus eventBus,
            DuelFieldRepository duelFieldRepository) {
        return new DuelFieldCommandUseCase(eventBus, duelFieldRepository);
    }
}
