package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.converters.PageToCategoriesPageConverter;
import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.pagination.CategoriesPage;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;

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
    public ResponseEntity<CategoriesPage> getCategory(@Param(value = "page") Integer page,
                                                      @Param(value = "size") Integer size) {

        PageRequest pageRequest = PageRequest.of(
                page == null ? 0 : page,
                size == null ? 25 : size,
                Sort.by("title")
        );

        return ResponseEntity.ok(new PageToCategoriesPageConverter().convert(service.getAll(pageRequest)));
    }

}
