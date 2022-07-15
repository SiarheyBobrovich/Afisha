package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.ResponseCountryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToResponseCountryDtoDtoConverter implements Converter<Country, ResponseCountryDto> {
    @Override
    public ResponseCountryDto convert(Country source) {
        return new ResponseCountryDto(source.getUuid(),
                source.getTitle(),
                source.getDtCreate(),
                source.getDtUpdate(),
                source.getDescription()
        );
    }
}
