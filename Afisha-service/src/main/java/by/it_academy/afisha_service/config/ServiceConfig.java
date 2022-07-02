package by.it_academy.afisha_service.config;

import by.it_academy.afisha_service.mappers.ClassifiersMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ClassifiersMapper classifiersMapper() {
        return new ClassifiersMapper();
    }
}
