package by.it_academy.user.services.api;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserCreateDto;
import by.it_academy.user.dto.response.ResponseUserDtoPage;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Validated
public interface IAdministrationService {

    /**
     * Method to validate a new user and save in database
     * @param newUser User to save
     */
    void save(@Valid UserCreateDto newUser);

    /**
     * Method to update saved user
     * @param updateUser User's params for update
     * @param uuid User's uuid for update
     * @param dtUpdate User's version for update
     */
    void update(@Valid UserCreateDto updateUser, UUID uuid, LocalDateTime dtUpdate);

    /**
     * Method to find page of saved users
     * @param pageable Current page to find
     * @return The current page of users
     */
    ResponseUserDtoPage getAll(Pageable pageable);

    /**
     * Method to find a saved user
     * @param uuid Current user's uuid to find
     * @return The saved user
     */
    User get(UUID uuid);
}
