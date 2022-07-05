package by.it_academy.afisha_service.services.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public interface IService<T, E> {

    void save(T t);

    Page<E> getAll(Pageable page);
}
