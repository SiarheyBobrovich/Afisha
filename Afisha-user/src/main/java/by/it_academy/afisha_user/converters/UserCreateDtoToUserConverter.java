package by.it_academy.afisha_user.converters;

import by.it_academy.afisha_user.dao.entity.User;
import by.it_academy.afisha_user.dto.request.UserCreateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserCreateDtoToUserConverter implements Converter<UserCreateDto, User> {

    @Override
    public User convert(UserCreateDto source) {
        LocalDateTime now = LocalDateTime.now();

        return User.create()
                .setUuid(UUID.randomUUID())
                .setNick(source.getNick())
                .setMail(source.getMail())
                .setPassword(source.getPassword())
                .setRole(source.getRole())
                .setStatus(source.getStatus())
                .setDtCreate(now)
                .setDtUpdate(now)
                .build();
    }
}
