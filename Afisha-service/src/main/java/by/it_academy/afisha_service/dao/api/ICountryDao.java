package by.it_academy.afisha_service.dao.api;

import by.it_academy.afisha_service.dao.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Country JPA
 */
@Repository
public interface ICountryDao extends JpaRepository<Country, UUID> {
}
