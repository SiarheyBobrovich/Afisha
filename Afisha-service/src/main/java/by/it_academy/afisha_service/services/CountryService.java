package by.it_academy.afisha_service.services;

import by.it_academy.afisha_service.dao.api.ICountryDao;
import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import by.it_academy.afisha_service.dto.ResponseCountryDto;
import by.it_academy.afisha_service.exceptions.ValidationException;
import by.it_academy.afisha_service.pagination.ResponseCountryPage;
import by.it_academy.afisha_service.services.api.IService;
import by.it_academy.afisha_service.utils.DefaultParamsUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CountryService implements IService<CountryDto, ResponseCountryDto> {

    private final ConversionService conversionService;
    private final ICountryDao dao;

    public CountryService(ConversionService conversionService, ICountryDao dao) {
        this.conversionService = conversionService;
        this.dao = dao;
    }

    @Override
    public void save(CountryDto countryDto) {
        ValidationException exception = new ValidationException();

        if (!countryDto.getTitle().matches("[\\p{L}\\d\\p{Punct}\\p{Blank}]++")) {
            exception.add(Map.entry("title", "не верный формат."));
        }

        if (!countryDto.getDescription().matches("[\\p{L}\\d\\p{Punct}\\p{Blank}]++")) {
            exception.add(Map.entry("description", "не верный формат."));
        }

        if (!exception.getErrors().isEmpty()) {
            throw exception;
        }

        Country country = conversionService.convert(countryDto, Country.class);
        DefaultParamsUtil.setDefaultParams(country);

        dao.save(country);
    }

    @Override
    public ResponseCountryPage getAll(Pageable page) {
        return conversionService.convert(dao.findAll(page), ResponseCountryPage.class);
    }

    @Override
    public Country get(UUID uuid) {
        return dao.getReferenceById(uuid);
    }
}
