package by.it_academy.user.services;

import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserCreateDto;
import by.it_academy.user.dto.response.ResponseUserDto;
import by.it_academy.user.pagination.ResponseUserDtoPage;
import by.it_academy.user.services.api.IUserAuthenticateService;
import by.it_academy.user.services.api.IUserInformationService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AdministrationUserService implements IUserInformationService {

    private final ConversionService conversionService;

    private final IUserDao userDao;

    public AdministrationUserService(ConversionService service, IUserAuthenticateService manager) {
        this.conversionService = service;
        this.manager = manager;
    }

    @Override
    public void save(UserCreateDto newUser) {
        User user = conversionService.convert(newUser, User.class);

        manager.createUser(user);
    }

    @Override
    public void update(UserCreateDto updateUser, UUID uuid, LocalDateTime dtUpdate) {
        User user = conversionService.convert(updateUser, User.class);

        user.setUuid(uuid);
        user.setDtUpdate(dtUpdate);

        manager.updateUser(user);
    }

    @Override
    public ResponseUserDtoPage getAll(Pageable pageable) {
        return conversionService.convert(
                manager.findAll(pageable), ResponseUserDtoPage.class
        );
    }

    @Override
    public ResponseUserDto get(UUID uuid) {

        return conversionService.convert(
                manager.findByUuid(uuid), ResponseUserDto.class
        );
    }
}
