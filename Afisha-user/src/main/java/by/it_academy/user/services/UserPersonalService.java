package by.it_academy.user.services;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserLoginDto;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.response.ResponseUserDto;
import by.it_academy.user.services.api.IUserAuthenticateService;
import by.it_academy.user.services.api.IUserPersonalService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPersonalService implements IUserPersonalService {

    private final ConversionService conversionService;
    private final PasswordEncoder encoder;

    private final IUserAuthenticateService manager;

    public UserPersonalService(IUserAuthenticateService manager,
                               ConversionService conversionService,
                               PasswordEncoder encoder) {
        this.manager = manager;
        this.conversionService = conversionService;
        this.encoder = encoder;
    }

    @Override
    public void save(UserRegistrationDto newUser) {
        User user = conversionService.convert(newUser, User.class);
        manager.createUser(user);
    }

    @Override
    public UserDetails login(UserLoginDto userLogin) {
        UserDetails userDetails = manager.loadUserByUsername(userLogin.getMail());
        if (userDetails != null &&
                encoder.matches(userLogin.getPassword(), userDetails.getPassword())) {
            return userDetails;
        }

        throw new SecurityException("Не верный логин или пороль");
    }

    @Override
    public ResponseUserDto getByMail(String mail) {
        return conversionService.convert(
                manager.loadUserByUsername(mail), ResponseUserDto.class
        );
    }
}
