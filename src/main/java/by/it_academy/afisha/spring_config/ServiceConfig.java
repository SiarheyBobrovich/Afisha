package by.it_academy.afisha.spring_config;

import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.services.mappers.EventMapper;
import by.it_academy.afisha.validators.EventDtoValidator;
import by.it_academy.afisha.validators.ValidatorContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServiceConfig {

    @Bean
    public ValidatorContainer validatorFactory(){
        return new ValidatorContainer().addValidator(
                EventDto.class, new EventDtoValidator()
        );
    }

    @Bean
    public EventMapper eventMapper() {
        return new EventMapper();
    }

}
