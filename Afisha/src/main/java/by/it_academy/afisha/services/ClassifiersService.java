package by.it_academy.afisha.services;

import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
@PropertySource("classifiers.properties")
public class ClassifiersService implements IClassifiersConnectService {

    @Autowired
    private Environment env;

    private final RestTemplate template;

    public ClassifiersService(RestTemplateBuilder templateBuilder) {
        this.template = templateBuilder.build();
    }

    @Override
    public void isValidUuid(String url, UUID uuid) {
        HttpStatus statusCode;
        ClientHttpRequest request;

        try {
            request = template.getRequestFactory()
                .createRequest(URI.create(url + uuid), HttpMethod.GET);

            try (ClientHttpResponse response = request.execute()) {
                statusCode = response.getStatusCode();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!statusCode.is2xxSuccessful()) {
            throw new EntityNotFoundException("В справочнике отсутствует uuid");
        }
    }

    @Override
    public void isValidCountry(UUID uuid) {
        isValidUuid(env.getProperty("country"), uuid);
    }

    @Override
    public void isValidCategory(UUID uuid) {
        isValidUuid(env.getProperty("category"), uuid);
    }
}
