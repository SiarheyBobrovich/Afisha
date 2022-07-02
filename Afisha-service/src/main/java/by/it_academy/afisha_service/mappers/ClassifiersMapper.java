package by.it_academy.afisha_service.mappers;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dao.entity.Country;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.dto.CountryDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClassifiersMapper {

    public Category getCategory(CategoryDto dto) {
        Category category = new Category();

        category.setUuid(UUID.randomUUID());
        category.setTitle(dto.getTitle());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtCreate());

        return category;
    }

    public Country getCountry(CountryDto dto) {
        Country country = new Country();

        country.setUuid(UUID.randomUUID());
        country.setTitle(dto.getTitle());
        country.setDescription(dto.getDescription());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtCreate());

        return country;
    }
}
