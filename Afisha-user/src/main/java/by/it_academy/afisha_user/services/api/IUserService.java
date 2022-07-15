package by.it_academy.afisha_user.services.api;

import by.it_academy.afisha_user.dto.request.UserCreateDto;
import by.it_academy.afisha_user.dto.response.ResponseUserDto;
import by.it_academy.afisha_user.pagination.ResponseUserDtoPage;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {

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
    ResponseUserDto get(UUID uuid);
}
