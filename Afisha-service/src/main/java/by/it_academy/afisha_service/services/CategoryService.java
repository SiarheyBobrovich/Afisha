package by.it_academy.afisha_service.services;

import by.it_academy.afisha_service.dao.api.ICategoryDao;
import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dto.CategoryDto;
import by.it_academy.afisha_service.mappers.ClassifiersMapper;
import by.it_academy.afisha_service.services.api.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements IService<CategoryDto, Category> {

    private final ClassifiersMapper mapper;
    private final ICategoryDao dao;

    public CategoryService(ClassifiersMapper mapper, ICategoryDao dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Override
    public void save(CategoryDto categoryDto) {
        dao.save(mapper.getCategory(categoryDto));
    }

    @Override
    public Page<Category> getAll(Pageable page) {
        return dao.findAll(page);
    }

    @Override
    public Category get(UUID uuid) {
        return dao.getReferenceById(uuid);
    }
}
