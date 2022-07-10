package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryOutDto;
import org.springframework.core.convert.converter.Converter;

public class CategoryToCategoryOutDtoConverter implements Converter<Category, CategoryOutDto> {

    @Override
    public CategoryOutDto convert(Category source) {
        return new CategoryOutDto(source.getUuid(),
                source.getTitle(),
                source.getDtCreate(),
                source.getDtUpdate()
        );
    }
}
