package by.it_academy.user.spring_config;

import by.it_academy.user.dao.api.IRoleDao;
import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.Authority;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dao.enums.Role;
import by.it_academy.user.dao.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Configuration
public class UsersStorageConfig {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Bean
    public CommandLineRunner commandLineRunner(PasswordEncoder encoder) {
        return args -> {

            LocalDateTime now = LocalDateTime.now();

             User user = User.builder()
                    .setUuid(UUID.randomUUID())
                    .setMail("admin@admin.admin")
                    .setNick("admin")
                    .setAuthorities(Set.of(Authority.of(Role.USER), Authority.of(Role.ADMIN)))
                    .setStatus(Status.ACTIVATED)
                    .setEnabled(true)
                    .setPassword(encoder.encode("123"))
                    .setCredentialsNonExpired(true)
                    .setAccountNonExpired(true)
                    .setAccountNonLocked(true)
                    .setDtCreate(now)
                    .setDtUpdate(now)
                    .build();



            try {
                userDao.save(user);

            }catch (DataIntegrityViolationException ignore) {
            }
        };

    }
}