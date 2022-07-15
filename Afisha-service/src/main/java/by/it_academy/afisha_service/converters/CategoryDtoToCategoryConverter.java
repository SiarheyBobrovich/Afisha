package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoToCategoryConverter implements Converter<CategoryDto, Category> {

    @Override
    public Category convert(CategoryDto source) {
        Category newCategory = new Category();
        newCategory.setTitle(source.getTitle());

        return newCategory;
    }
}
