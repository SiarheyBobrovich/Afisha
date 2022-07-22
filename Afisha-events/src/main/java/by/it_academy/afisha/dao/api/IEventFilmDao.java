package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventFilmDao extends JpaRepository<EventFilm, UUID> {

    /**
     * Method to get page of Events(film)
     * @param type Action's type parameter
     * @param pageable Current page
     * @return Current page of Events(film) that are found
     */
    @Query
    Page<EventFilm> findAllByFilmType(Type type,Pageable pageable);

    @Query
    Page<EventFilm> findByFilmTypeAndStatus(Type type, Status status, Pageable pageable);

    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND (e.status = ?2 OR e.author = ?3)")
    Page<EventFilm> findByFilmTypeAndStatusOrAuthor(Type type, Status status, String author, Pageable pageable);

    @Query("select e from EventFilm e where e.uuid = ?1 and e.author = ?2")
    Optional<EventFilm> findByUuidAndAuthor(UUID uuid, String author);


}