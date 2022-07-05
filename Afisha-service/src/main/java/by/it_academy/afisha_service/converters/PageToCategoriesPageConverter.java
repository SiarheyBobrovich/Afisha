package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.pagination.CategoriesPage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class PageToCategoriesPageConverter implements Converter<Page<Category>, CategoriesPage> {

    @Override
    public CategoriesPage convert(Page<Category> source) {
        return CategoriesPage.create()
                .setPage(source.getNumber())
                .setSize(source.getSize())
                .setTotalPages(source.getTotalPages())
                .setTotalElements(source.getTotalElements())
                .setFirst(source.isFirst())
                .setNumberOfElements(source.getNumberOfElements())
                .setLast(source.isLast())
                .setContent(source.getContent())
                .build();
    }

}
