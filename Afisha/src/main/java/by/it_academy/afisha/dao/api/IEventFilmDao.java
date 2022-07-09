package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventFilmDao extends JpaRepository<EventFilm, UUID> {

    @Query
    Page<EventFilm> findAllByFilmType(Type type,Pageable pageable);

}
