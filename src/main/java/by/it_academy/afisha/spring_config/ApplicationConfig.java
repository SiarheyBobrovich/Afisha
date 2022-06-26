package by.it_academy.afisha.spring_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ControllerConfig.class)
public class ApplicationConfig {

}
