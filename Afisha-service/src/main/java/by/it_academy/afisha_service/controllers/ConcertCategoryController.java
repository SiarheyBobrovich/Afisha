package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.converters.CategoryToResponseCategoryDtoConverter;
import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.dto.ResponseCategoryDto;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/concert/category")
public class ConcertCategoryController {

    private final IService<CategoryDto, ResponseCategoryDto> service;

    public ConcertCategoryController(IService<CategoryDto, ResponseCategoryDto> service) {
        this.service = service;
    }

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCategory(@RequestBody CategoryDto category) {
        service.save(category);
    }

    @GetMapping
    public ResponseEntity<Object> getCategory(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("title")
        );

        return ResponseEntity.ok()
                .body(service.getAll(pageRequest));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> isValidCategory(@PathVariable UUID uuid) {
        ResponseCategoryDto outDto = new CategoryToResponseCategoryDtoConverter().convert((Category) service.get(uuid));
        return ResponseEntity.ok(outDto);
    }
}
