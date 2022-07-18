package by.it_academy.user.services.api;

import by.it_academy.user.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.UUID;

public interface IUserAuthenticateService extends UserDetailsManager {

    Page<User> findAll(Pageable page);

    User findByUuid(UUID uuid);

}
