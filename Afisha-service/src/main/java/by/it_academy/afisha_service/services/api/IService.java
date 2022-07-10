package by.it_academy.afisha_service.services.api;

import by.it_academy.afisha_service.dao.entity.AbstractClassifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IService<T, E> {

    void save(T t);

    Page<E> getAll(Pageable page);

    AbstractClassifier get(UUID uuid);
}
