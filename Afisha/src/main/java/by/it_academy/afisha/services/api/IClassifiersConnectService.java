package by.it_academy.afisha.services.api;

import by.it_academy.afisha.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface IClassifiersConnectService {

    /**
     * UUID validation method in custom classifier service
     * @param url Classifiers service's url
     * @param uuid Validated uuid
     * @throws EntityNotFoundException If uuid isn't valid
     */
    void isValidUuid(String url, UUID uuid) throws EntityNotFoundException;

    /**
     * UUID validation method in classifier service
     * @param uuid Country uuid
     * @throws EntityNotFoundException If uuid isn't valid
     */
    void isValidCountry(UUID uuid) throws EntityNotFoundException;

    /**
     * UUID validation method in classifier service
     * @param uuid Category uuid
     * @throws EntityNotFoundException If uuid isn't valid
     */
    void isValidCategory(UUID uuid) throws EntityNotFoundException;
}