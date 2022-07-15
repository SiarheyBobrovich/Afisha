package by.it_academy.afisha_service.services.api;

import by.it_academy.afisha_service.dao.entity.AbstractClassifier;
import by.it_academy.afisha_service.pagination.ResponsePage;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IService<T, E> {

    /**
     * Method to validation and save T-object to dao
     * @param t An object to validate and save
     */
    void save(T t);

    /**
     * Method to get page of E objects
     * @param page A current page
     * @return The current page of E objects
     */
    ResponsePage<E> getAll(Pageable page);

    /**
     * Method to get a classifier by id
     * @param uuid The classifier id
     * @return The classifier from dao
     */
    AbstractClassifier get(UUID uuid);
}
