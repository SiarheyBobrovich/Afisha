package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryOutDto;
import org.springframework.core.convert.converter.Converter;

public class CountryToCountryOutDtoConverter implements Converter<Country, CountryOutDto> {
    @Override
    public CountryOutDto convert(Country source) {
        return new CountryOutDto(source.getUuid(),
                source.getTitle(),
                source.getDtCreate(),
                source.getDtUpdate(),
                source.getDescription()
        );
    }
}
