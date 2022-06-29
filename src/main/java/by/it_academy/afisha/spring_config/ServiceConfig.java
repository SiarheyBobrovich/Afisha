package by.it_academy.afisha.spring_config;

import by.it_academy.afisha.dao.api.IAbstractDao;
import by.it_academy.afisha.dao.api.IConcertEventDao;
import by.it_academy.afisha.dao.api.IFilmEventDao;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.services.EventService;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.mappers.EventMapper;
import by.it_academy.afisha.validators.EventDtoValidator;
import by.it_academy.afisha.validators.ValidatorFactory;
import by.it_academy.afisha.validators.api.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    @Bean
    public IAfishaService getService(IAbstractDao abstractDao,
                                     IFilmEventDao filmEventDao,
                                     IConcertEventDao concertEventDao,
                                     EventMapper mapper,
                                     ValidatorFactory factory
    ) {

        return new EventService(abstractDao, concertEventDao, filmEventDao, mapper, factory);
    }

    @Bean
    public EventMapper getMapper() {
        return new EventMapper();
    }


    @Bean
    public ValidatorFactory validatorFactory(){
        return new ValidatorFactory().addValidator(
                EventDto.class, new EventDtoValidator()
        );
    }
}
