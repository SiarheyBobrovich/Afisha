package by.it_academy.afisha_user.converters;

import by.it_academy.afisha_user.dao.entity.User;
import by.it_academy.afisha_user.dto.response.ResponseUserDto;
import by.it_academy.afisha_user.pagination.ResponseUserDtoPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageUserToResponseUserDtoPage implements Converter<Page<User>, ResponseUserDtoPage> {

    private final Converter<User, ResponseUserDto> converter;

    public PageUserToResponseUserDtoPage(Converter<User, ResponseUserDto> converter) {
        this.converter = converter;
    }

    @Override
    public ResponseUserDtoPage convert(Page<User> source) {
        return new ResponseUserDtoPage(
                source.getNumber(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.getContent()
                        .stream()
                        .map(converter::convert)
                        .collect(Collectors.toList())
        );
    }
}
