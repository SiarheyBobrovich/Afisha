package by.it_academy.afisha.services;

import by.it_academy.afisha.services.api.IClassifiersConnectService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
public class ClassifiersService implements IClassifiersConnectService {

    @Override
    public boolean isValidUuid(String url, UUID uuid) {
        boolean result;
        final HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) new URL(url)
                    .openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("uuid", uuid.toString());

            result = connection.getResponseCode() == 200;

        }catch (IOException e) {
            result = false;
        }

        return result;
    }

}
