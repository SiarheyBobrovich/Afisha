package by.it_academy.user.converters;

import by.it_academy.user.dao.entity.User;
import by.it_academy.user.dto.response.ResponseUserDto;
import by.it_academy.user.dto.response.ResponseUserDtoPage;
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
