package by.it_academy.afisha_service.services;

import by.it_academy.afisha_service.dao.api.ICountryDao;
import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import by.it_academy.afisha_service.mappers.ClassifiersMapper;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Country country = mapper.getCountry(countryDto);
        dao.save(country);
    }

    @Override
    public List<Country> getAll() {
        return  dao.findAll();
    }
}
