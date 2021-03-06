package by.it_academy.afisha_service;

import by.it_academy.afisha_service.dao.api.ICategoryDao;
import by.it_academy.afisha_service.dao.api.ICountryDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		ICategoryDao.class, ICountryDao.class
})
public class ServiceApplicationMain {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ServiceApplicationMain.class, args);
	}
}
