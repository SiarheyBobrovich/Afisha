package by.it_academy.user.converters;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.response.ResponseUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseUserDtoConverter implements Converter<User, ResponseUserDto> {

    @Override
    public ResponseUserDto convert(User source) {
        final StringBuilder roleBuilder = new StringBuilder();

        source.getAuthorities().forEach(x ->
                roleBuilder.append(x.getAuthority())
                        .append(", ")
        );

        return ResponseUserDto.create()
                .setUuid(source.getUuid())
                .setMail(source.getMail())
                .setNick(source.getNick())
                .setStatus(source.getStatus())
                .setDtCreate(source.getDtCreate())
                .setDtUpdate(source.getDtUpdate())
                .setRole(roleBuilder.substring(0, roleBuilder.length() - 2))
                .build();
    }
}
