package by.it_academy.afisha.spring_config;

import by.it_academy.afisha.dao.api.IConcertEventDao;
import by.it_academy.afisha.dao.api.IFilmEventDao;
import by.it_academy.afisha.services.EventService;
import by.it_academy.afisha.services.api.IAfishaService;
import by.it_academy.afisha.services.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    @Bean
    public IAfishaService getService(@Autowired IFilmEventDao filmEventDao,
                                     @Autowired IConcertEventDao concertEventDao,
                                     @Autowired EventMapper mapper) {

        return new EventService(filmEventDao, concertEventDao, mapper);
    }

    @Bean
    public EventMapper getMapper() {
        return new EventMapper();
    }
}
