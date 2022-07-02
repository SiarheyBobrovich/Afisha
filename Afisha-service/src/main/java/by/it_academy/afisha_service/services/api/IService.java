package by.it_academy.afisha_service.services.api;

import org.springframework.stereotype.Service;

import java.util.List;

public interface IService<T, E> {

    void save(T t);

    List<E> getAll();
}
