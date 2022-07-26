package by.it_academy.afisha_service.dao.api;

import by.it_academy.afisha_service.dao.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Concert's category JPA
 */
@Repository
public interface ICategoryDao extends JpaRepository<Category, UUID> {

    @Query
    boolean existsByTitle(String title);

}
