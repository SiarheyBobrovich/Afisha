package by.it_academy.afisha_user.converters;

import by.it_academy.afisha_user.dao.entity.User;
import by.it_academy.afisha_user.dto.response.ResponseUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseUserDtoConverter implements Converter<User, ResponseUserDto> {

    @Override
    public ResponseUserDto convert(User source) {
        return ResponseUserDto.create()
                .setUuid(source.getUuid())
                .setMail(source.getMail())
                .setNick(source.getNick())
                .setRole(source.getRole())
                .setStatus(source.getStatus())
                .setDtCreate(source.getDtCreate())
                .setDtUpdate(source.getDtUpdate())
                .build();
    }
}
