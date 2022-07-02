package by.it_academy.afisha_service.dao.api;

import by.it_academy.afisha_service.dao.entity.Category;
import by.it_academy.afisha_service.dao.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryDao extends JpaRepository<Category, UUID> {
}
