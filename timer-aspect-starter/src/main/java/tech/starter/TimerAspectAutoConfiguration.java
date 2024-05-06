package tech.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerAspectAutoConfiguration {
    @Bean
    TimerAspect timerAspect() {
        return new TimerAspect();
    }
}
