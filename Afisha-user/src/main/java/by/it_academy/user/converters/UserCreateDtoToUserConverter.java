package by.it_academy.user.converters;

import by.it_academy.user.utils.LocalDateTimeUtils;
import by.it_academy.user.converters.api.AbstractConverterWithEncoder;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.request.UserCreateDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Component
public class UserCreateDtoToUserConverter extends AbstractConverterWithEncoder<UserCreateDto, User> {

    public UserCreateDtoToUserConverter(PasswordEncoder encoder) {
        super(encoder);
    }

    @Override
    public User convert(UserCreateDto source) {
        LocalDateTime now = LocalDateTime.now();

        return User.builder()
                .setUuid(UUID.randomUUID())
                .setNick(source.getNick())
                .setMail(source.getMail())
                .setPassword(encoder.encode(source.getPassword()))
                .setStatus(source.getStatus())
                .setAuthorities(Set.of(source.getRole()))
                .setDtCreate(now)
                .setDtUpdate(LocalDateTimeUtils.convertNanosToMillis(now))
                .setAccountNonLocked(true)
                .setAccountNonExpired(true)
                .setCredentialsNonExpired(true)
                .setEnabled(true)
                .build();
    }
}
