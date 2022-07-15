package by.it_academy.afisha_user.services;

import by.it_academy.afisha_user.dao.api.IUserDao;
import by.it_academy.afisha_user.dao.entity.User;
import by.it_academy.afisha_user.dto.request.UserCreateDto;
import by.it_academy.afisha_user.dto.response.ResponseUserDto;
import by.it_academy.afisha_user.pagination.ResponseUserDtoPage;
import by.it_academy.afisha_user.services.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserInformationService implements IUserService {

    private final ConversionService conversionService;

    private final IUserDao dao;

    public UserInformationService(ConversionService service, IUserDao dao) {
        this.conversionService = service;
        this.dao = dao;
    }

    @Override
    public void save(UserCreateDto newUser) {
        User user = conversionService.convert(newUser, User.class);

        dao.save(user);
    }

    @Override
    public void update(UserCreateDto updateUser, UUID uuid, LocalDateTime dtUpdate) {
        User currentUser = dao.findById(uuid).orElseThrow(EntityNotFoundException::new);

        if (!currentUser.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalStateException("User was updated before");
        }

        currentUser.setMail(updateUser.getMail());
        currentUser.setNick(updateUser.getNick());
        currentUser.setRole(updateUser.getRole());
        currentUser.setStatus(updateUser.getStatus());
        currentUser.setPassword(updateUser.getPassword());

        dao.save(currentUser);
    }

    @Override
    public ResponseUserDtoPage getAll(Pageable pageable) {
        return conversionService.convert(
                dao.findAll(), ResponseUserDtoPage.class
        );
    }

    @Override
    public ResponseUserDto get(UUID uuid) {
        return conversionService.convert(
                dao.findById(uuid).orElseThrow(EntityNotFoundException::new), ResponseUserDto.class
        );
    }
}
