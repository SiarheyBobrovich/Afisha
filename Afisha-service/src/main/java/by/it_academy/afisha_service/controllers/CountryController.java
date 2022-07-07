package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.converters.PageToClassifiersPageConverter;
import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import by.it_academy.afisha_service.pagination.ClassifiersPage;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ClassifiersPage<Country>> getCountry(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("title")
        );

        Page<Country> allCountries = service.getAll(pageRequest);

        return ResponseEntity.ok().body(
                new PageToClassifiersPageConverter<Country>().convert(allCountries)
        );
    }
}
