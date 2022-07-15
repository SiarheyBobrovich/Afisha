package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.ResponseCountryDto;
import by.it_academy.afisha_service.pagination.ResponseCountryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageCountryToResponseCountryPageConverter implements Converter<Page<Country>, ResponseCountryPage> {

    @Autowired
    private Converter<Country, ResponseCountryDto> converter;

    @Override
    public ResponseCountryPage convert(Page<Country> source) {
        return new ResponseCountryPage(source.getNumber(),
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
