package by.it_academy.afisha.services.api;

import java.util.UUID;

public interface IClassifiersConnectService {

    void isValidUuid(String url, UUID uuid);

    void isValidCountry(UUID uuid);
    void isValidCategory(UUID uuid);
}