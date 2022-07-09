package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.pagination.ClassifiersPage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class PageToClassifiersPageConverter<T> implements Converter<Page<T>, ClassifiersPage<T>> {

    @Override
    public ClassifiersPage<T> convert(Page<T> source) {
        return new ClassifiersPage<>(source.getNumber(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.getContent());
    }
}
