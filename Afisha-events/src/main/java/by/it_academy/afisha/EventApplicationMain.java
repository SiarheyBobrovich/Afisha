package by.it_academy.afisha;

import by.it_academy.afisha.dao.api.IEvenConcertDao;
import by.it_academy.afisha.dao.api.IEventFilmDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(
        basePackageClasses = {
                IEventFilmDao.class, IEvenConcertDao.class
        }
)
public class EventApplicationMain {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Locale.setDefault(new Locale("ru", "RU"));
        SpringApplication.run(EventApplicationMain.class, args);
    }
}
