package by.it_academy.afisha.services;

import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
@PropertySource("classifiers.properties")
public class ClassifiersService implements IClassifiersConnectService {

    @Autowired
    Environment env;
    @Override
    public boolean isValidUuid(String url, UUID uuid) {
        boolean result;
        final HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) new URL(url + uuid)
                    .openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            result = connection.getResponseCode() == 200;

        }catch (IOException e) {
            result = false;
        }

        return result;
    }

    @Override
    public void isValidCountry(UUID uuid) {
        if (!isValidUuid(env.getProperty("country"), uuid)) {
            throw new EntityNotFoundException("Uuid страны не найден");
        }
    }

    @Override
    public void isValidCategory(UUID uuid) {
        if (!isValidUuid(env.getProperty("category"), uuid)) {
            throw new EntityNotFoundException("Uuid категории не найден");
        }
    }
}
