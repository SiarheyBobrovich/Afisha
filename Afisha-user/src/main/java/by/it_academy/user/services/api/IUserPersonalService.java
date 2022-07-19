package by.it_academy.user.services.api;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserLoginDto;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.response.ResponseUserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface IUserPersonalService {

    /**
     * Method to validate a new user and save in database
     * @param newUser User to save
     */
    void save(@Valid UserRegistrationDto newUser);

    /**
     * Method to login user
     * @param userLogin User's params for update
     */
    User login(@Valid UserLoginDto userLogin);

    /**
     * Method to find user by mail
     * @param mail User's mail
     * @return Information about
     */
    ResponseUserDto getByMail(String mail);
}
