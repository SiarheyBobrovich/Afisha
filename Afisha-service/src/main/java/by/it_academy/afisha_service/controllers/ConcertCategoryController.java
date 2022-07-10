package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.converters.CategoryToCategoryOutDtoConverter;
import by.it_academy.afisha_service.converters.PageToClassifiersPageConverter;
import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.dto.CategoryOutDto;
import by.it_academy.afisha_service.pagination.ClassifiersPage;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.Page;
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

    private final IService<CategoryDto, Category> service;

    public ConcertCategoryController(IService<CategoryDto, Category> service) {
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
    public ResponseEntity<ClassifiersPage<Category>> getCategory(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "25") Integer size) {

        PageRequest pageRequest = PageRequest.of(
                page, size, Sort.by("title")
        );

        Page<Category> allCategories = service.getAll(pageRequest);

        return ResponseEntity.ok().body(
                new PageToClassifiersPageConverter<Category>().convert(allCategories)
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> isValidCategory(@PathVariable UUID uuid) {
        CategoryOutDto outDto = new CategoryToCategoryOutDtoConverter().convert((Category) service.get(uuid));
        return ResponseEntity.ok(outDto);
    }
}
