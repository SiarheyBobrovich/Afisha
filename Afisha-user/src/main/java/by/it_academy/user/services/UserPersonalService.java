package by.it_academy.user.services;

import by.it_academy.user.dao.api.IRoleDao;
import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserLoginDto;
import by.it_academy.user.dto.request.UserRegistrationDto;
import by.it_academy.user.dto.response.ResponseUserDto;
import by.it_academy.user.services.api.IUserPersonalService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserPersonalService implements IUserPersonalService {

    private final ConversionService conversionService;
    private final PasswordEncoder encoder;

    private final IUserDao userDao;
    private final IRoleDao roleDao;

    public UserPersonalService(ConversionService conversionService,
                               PasswordEncoder encoder,
                               IUserDao userDao,
                               IRoleDao roleDao) {
        this.conversionService = conversionService;
        this.encoder = encoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void save(UserRegistrationDto newUser) {
        if (userDao.existsByMail(newUser.getMail())) {
            throw new EntityExistsException("Пользователь уже существует");
        }

        User user = conversionService.convert(newUser, User.class);

        setAuthoritiesFromDao(user);

        userDao.save(user);
    }

    @Override
    public User login(UserLoginDto userLogin) {
        User currentUser = userDao.findByMail(userLogin.getMail());

        if (currentUser == null &&
                !encoder.matches(userLogin.getPassword(), currentUser.getPassword())) {
            throw new SecurityException("Не верный логин или пороль");
        }

        return currentUser;
    }

    @Override
    public ResponseUserDto getByMail(String mail) {
        return conversionService.convert(
                userDao.findByMail(mail), ResponseUserDto.class
        );
    }

    private void setAuthoritiesFromDao(User user) {
        user.setAuthorities(
                user.getAuthorities()
                        .stream()
                        .map(x -> roleDao.findByAuthority(x.getAuthority()))
                        .collect(Collectors.toSet())
        );
    }
}
