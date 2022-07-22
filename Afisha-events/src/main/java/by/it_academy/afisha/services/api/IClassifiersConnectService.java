package by.it_academy.afisha.services.api;

import by.it_academy.afisha.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public interface IClassifiersConnectService {

    /**
     * UUID validation method in custom classifier service
     * @param url Classifiers service's url
     * @param uuid Validated uuid
     * @throws EntityNotFoundException If uuid isn't valid
     */
    HttpStatus getHttpStatus(String url, UUID uuid) throws EntityNotFoundException;

    /**
     * UUID validation method in classifier service
     * @param uuid Country uuid
     * @throws EntityNotFoundException If uuid isn't valid
     */
    boolean isValidCountry(UUID uuid) throws EntityNotFoundException;

    /**
     * UUID validation method in classifier service
     * @param uuid Category uuid
     * @throws EntityNotFoundException If uuid isn't valid
     */
    boolean isValidCategory(UUID uuid) throws EntityNotFoundException;
}