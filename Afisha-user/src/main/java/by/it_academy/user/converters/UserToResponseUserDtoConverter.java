package by.it_academy.user.converters;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.response.ResponseUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class UserToResponseUserDtoConverter implements Converter<User, ResponseUserDto> {

    @Override
    public ResponseUserDto convert(User source) {
        return ResponseUserDto.create()
                .setUuid(source.getUuid())
                .setMail(source.getMail())
                .setNick(source.getNick())
                .setStatus(source.getStatus())
                .setDtCreate(source.getDtCreate())
                .setDtUpdate(source.getDtUpdate())
                .setRole(source.getAuthorities().stream().max((x, y) -> (int) (x.getId() - y.getId())).get().getAuthority())
                .build();
    }
}
