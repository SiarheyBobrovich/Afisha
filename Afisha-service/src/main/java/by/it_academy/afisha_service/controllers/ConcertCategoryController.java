package by.it_academy.afisha_service.controllers;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Category> getCategory() {
        return service.getAll();
    }

}
