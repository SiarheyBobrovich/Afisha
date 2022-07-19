package by.it_academy.user.converters;

import by.it_academy.user.controllers.utils.LocalDateTimeUtils;
import by.it_academy.user.converters.api.AbstractConverterWithEncoder;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dao.entity.Role;
import by.it_academy.user.dao.enums.Status;
import by.it_academy.user.dto.request.UserRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Component
public class UserRegistrationDtoToUserConverter extends AbstractConverterWithEncoder<UserRegistrationDto, User> {

    public UserRegistrationDtoToUserConverter(PasswordEncoder encoder) {
        super(encoder);
    }

    @Override
    public User convert(UserRegistrationDto source) {
        LocalDateTime now = LocalDateTime.now();

        return User.builder()
                .setUuid(UUID.randomUUID())
                .setNick(source.getNick())
                .setMail(source.getMail())
                .setPassword(encoder.encode(source.getPassword()))
                .setAuthorities(Set.of(Role.of(by.it_academy.user.dao.enums.Role.USER)))
                .setDtCreate(now)
                .setDtUpdate(LocalDateTimeUtils.convertNanosToMillis(now))
                .setStatus(Status.ACTIVATED)
                .setAccountNonLocked(true)
                .setAccountNonExpired(true)
                .setCredentialsNonExpired(true)
                .setEnabled(true)
                .build();
    }
}
