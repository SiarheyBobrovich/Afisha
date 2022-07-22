package by.it_academy.afisha_service.services;

import by.it_academy.afisha_service.dao.api.ICategoryDao;
import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.dto.ResponseCategoryDto;
import by.it_academy.afisha_service.exceptions.ValidationException;
import by.it_academy.afisha_service.pagination.ResponseCategoryPage;
import by.it_academy.afisha_service.services.api.IService;
import by.it_academy.afisha_service.utils.DefaultParamsUtil;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CategoryService implements IService<CategoryDto, ResponseCategoryDto> {

    private final ConversionService conversionService;
    private final ICategoryDao dao;

    public CategoryService(ConversionService conversionService, ICategoryDao dao) {
        this.conversionService = conversionService;
        this.dao = dao;
    }

    @Override
    public void save(CategoryDto categoryDto) {
        if (!categoryDto.getTitle().matches("[\\p{L}\\d\\p{Punct}\\p{Blank}]++")) {
            ValidationException exception = new ValidationException();
            exception.add(Map.entry("title", "не верный формат."));

            throw exception;
        }

        Category newCategory = conversionService.convert(categoryDto, Category.class);

        DefaultParamsUtil.setDefaultParams(newCategory);

        dao.save(newCategory);
    }

    @Override
    public ResponseCategoryPage getAll(Pageable page) {
        return conversionService.convert(dao.findAll(page), ResponseCategoryPage.class);
    }

    @Override
    public Category get(UUID uuid) {
        return dao.getReferenceById(uuid);
    }
}
