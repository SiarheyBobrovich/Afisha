package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.converters.CountryToResponseCountryDtoDtoConverter;
import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CountryDto;
import by.it_academy.afisha_service.dto.ResponseCountryDto;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    private final IService<CountryDto, ResponseCountryDto> service;

    public CountryController(IService<CountryDto, ResponseCountryDto> service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCountry(@RequestBody CountryDto country) {
        service.save(country);
    }

    @GetMapping
    public ResponseEntity<Object> getCountry(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("title")
        );

        return ResponseEntity.ok().body(service.getAll(pageRequest));

    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> isValidCountry(@PathVariable UUID uuid) {
        ResponseCountryDto convert = new CountryToResponseCountryDtoDtoConverter().convert((Country) service.get(uuid));
        return ResponseEntity.ok(convert);
    }
}
