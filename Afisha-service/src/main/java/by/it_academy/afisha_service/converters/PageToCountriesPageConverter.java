package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.pagination.CountriesPage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class PageToCountriesPageConverter implements Converter<Page<Country>, CountriesPage> {

    @Override
    public CountriesPage convert(Page<Country> source) {
        return CountriesPage.create()
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
