package by.it_academy.afisha_service.converters;

import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryDtoToCountryConverter implements Converter<CountryDto, Country> {

    @Override
    public Country convert(CountryDto source) {
        Country newCountry = new Country();
        newCountry.setTitle(source.getTitle());
        newCountry.setDescription(source.getDescription());

        return newCountry;
    }
}
