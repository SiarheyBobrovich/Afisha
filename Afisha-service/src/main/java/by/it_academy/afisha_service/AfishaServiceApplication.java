package by.it_academy.afisha_service;

import by.it_academy.afisha_service.dao.api.ICategoryDao;
import by.it_academy.afisha_service.dao.api.ICountryDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		ICategoryDao.class, ICountryDao.class
})
public class AfishaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfishaServiceApplication.class, args);
	}

}
