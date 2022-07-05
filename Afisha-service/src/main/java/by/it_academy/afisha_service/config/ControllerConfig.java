package by.it_academy.afisha_service.config;

import by.it_academy.afisha_service.controllers.utils.LocalDateTimeDeserializer;
import by.it_academy.afisha_service.controllers.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapperFactoryBean().getObject());
        converters.add(converter);
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperFactoryBean mapperFactoryBean) {
        return mapperFactoryBean.getObject();
    }

    @Bean
    public Jackson2ObjectMapperFactoryBean mapperFactoryBean() {
        Jackson2ObjectMapperFactoryBean factoryBean = new Jackson2ObjectMapperFactoryBean();

        factoryBean.setObjectMapper(new ObjectMapper());
        factoryBean.setPropertyNamingStrategy(SNAKE_CASE);
        factoryBean.setModulesToInstall(JavaTimeModule.class);
        factoryBean.setSerializersByType(Map.of(LocalDateTime.class , new LocalDateTimeSerializer()));

        return factoryBean;
    }

    @Bean
    public MappingJackson2HttpMessageConverter converter(Jackson2ObjectMapperFactoryBean objectMapper) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper.getObject());

        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public RequestMappingHandlerAdapter adapter( MappingJackson2HttpMessageConverter converter) {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setMessageConverters(List.of(converter));

        return requestMappingHandlerAdapter;
    }
}
