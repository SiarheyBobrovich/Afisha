package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    private final IService<CountryDto, Country> service;

    public CountryController(IService<CountryDto, Country> service) {
        this.service = service;
    }

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCountry(@RequestBody CountryDto country) {
        service.save(country);
    }

    @GetMapping
    public List<Country> getCountry() {
        return service.getAll();
    }

}
