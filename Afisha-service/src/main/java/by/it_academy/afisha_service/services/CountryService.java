package by.it_academy.afisha_service.services;

import by.it_academy.afisha_service.dao.api.ICountryDao;
import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import by.it_academy.afisha_service.exceptions.ValidationException;
import by.it_academy.afisha_service.mappers.ClassifiersMapper;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CountryService implements IService<CountryDto, Country> {

    private final ClassifiersMapper mapper;
    private final ICountryDao dao;

    public CountryService(ClassifiersMapper mapper, ICountryDao dao) {
        this.mapper = mapper;
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

        Country country = mapper.getCountry(countryDto);

        dao.save(country);
    }

    @Override
    public Page<Country> getAll(Pageable page) {
        return dao.findAll(page);
    }

    @Override
    public Country get(UUID uuid) {
        return dao.getReferenceById(uuid);
    }
}
