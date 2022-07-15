package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.ResponseCategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToResponseCategoryDtoConverter implements Converter<Category, ResponseCategoryDto> {

    @Override
    public ResponseCategoryDto convert(Category source) {
        return new ResponseCategoryDto(source.getUuid(),
                source.getTitle(),
                source.getDtCreate(),
                source.getDtUpdate()
        );
    }
}
