package by.it_academy.afisha.services;

import by.it_academy.afisha.exceptions.CategoryNotFoundException;
import by.it_academy.afisha.exceptions.CountryNotFoundException;
import by.it_academy.afisha.utils.JwtTokenUtil;
import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class ClassifiersService implements IClassifiersConnectService {

    private final String countryServiceUrl;
    private final String categoryServiceUrl;
    private final RestTemplate template;

    public ClassifiersService(RestTemplateBuilder templateBuilder,
                              @Value("${country.service.url}") String countryServiceUrl,
                              @Value("${category.service.url}") String categoryServiceUrl) {
        this.template = templateBuilder.build();
        this.countryServiceUrl = countryServiceUrl;
        this.categoryServiceUrl = categoryServiceUrl;
    }

    @Override
    public HttpStatus getHttpStatus(String url, UUID uuid) throws EntityNotFoundException {
        HttpStatus statusCode;
        ClientHttpRequest request;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = JwtTokenUtil.generateAccessToken(userDetails);

        try {
            request = template.getRequestFactory()
                .createRequest(URI.create(url + uuid), HttpMethod.GET);

            request.getHeaders()
                    .put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + token));

            try (ClientHttpResponse response = request.execute()) {
                statusCode = response.getStatusCode();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return statusCode;
    }

    @Override
    public void isValidCountry(UUID uuid) {
        if (!getHttpStatus(countryServiceUrl, uuid).is2xxSuccessful()) {
            throw new CountryNotFoundException();
        }
    }

    @Override
    public void isValidCategory(UUID uuid) {
        if (!getHttpStatus(categoryServiceUrl, uuid).is2xxSuccessful()) {
            throw new CategoryNotFoundException();
        }
    }
}
