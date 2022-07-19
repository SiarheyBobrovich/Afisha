package by.it_academy.user.services;

import by.it_academy.user.dao.api.IRoleDao;
import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserCreateDto;
import by.it_academy.user.dto.response.ResponseUserDto;
import by.it_academy.user.pagination.ResponseUserDtoPage;
import by.it_academy.user.services.api.IAdministrationService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AdministrationUserService implements IAdministrationService {

    private final ConversionService conversionService;

    private final IUserDao userDao;
    private final IRoleDao roleDao;

    public AdministrationUserService(ConversionService service, IUserDao userDao, IRoleDao roleDao) {
        this.conversionService = service;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void save(UserCreateDto newUser) {
        if (userDao.existsByMail(newUser.getMail())) {
            throw new EntityExistsException("Mail уже зарегестрирован");
        }

        User user = conversionService.convert(newUser, User.class);

        setAuthoritiesFromDao(user);

        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(UserCreateDto updateUser, UUID uuid, LocalDateTime dtUpdate) {
        if (userDao.existsByMail(updateUser.getMail())) {
            throw new EntityExistsException("Mail уже зарегестрирован");
        }

        User currentUser = userDao.findById(uuid).orElseThrow(() ->
                new EntityNotFoundException("Не корректный uuid")
        );

        if (!currentUser.getDtUpdate().equals(dtUpdate)) {
            throw new OptimisticLockException("Кто-то уже успел обновить событие");
        }

        User updatedUser = conversionService.convert(updateUser, User.class);

        updatedUser.setUuid(uuid);
        updatedUser.setDtUpdate(dtUpdate);
        setAuthoritiesFromDao(updatedUser);

        userDao.save(updatedUser);
    }

    @Override
    public ResponseUserDtoPage getAll(Pageable pageable) {
        return conversionService.convert(
                userDao.findAll(pageable), ResponseUserDtoPage.class
        );
    }

    @Override
    public ResponseUserDto get(UUID uuid) {
        return conversionService.convert(
                userDao.findById(uuid), ResponseUserDto.class
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
