package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.ResponseCategoryDto;
import by.it_academy.afisha_service.pagination.ResponseCategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageCategoryToResponseCategoryPageConverter implements Converter<Page<Category>, ResponseCategoryPage> {

    @Autowired
    private Converter<Category, ResponseCategoryDto> converter;

    @Override
    public ResponseCategoryPage convert(Page<Category> source) {
        return new ResponseCategoryPage(source.getNumber(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.getContent().stream().map(converter::convert).collect(Collectors.toList())
                );
    }

}
