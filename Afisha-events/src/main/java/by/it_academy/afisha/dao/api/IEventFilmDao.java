package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventFilmDao extends JpaRepository<EventFilm, UUID> {

    /**
     * Method to get page of events(film)
     * @param type Film type
     * @param uuid Current film's uuid
     * @return An event(film) that is found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND e.uuid = ?2")
    Optional<EventFilm> findByFilmTypeAndUuid(Type type, UUID uuid);

    /**
     * Method to get page of event(film)
     * @param type Film type
     * @param uuid Current film's uuid
     * @param author Current author
     * @return An event(film) that is found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND e.uuid = ?2 AND e.author = ?3")
    Optional<EventFilm> findByFilmTypeAndUuidAndAuthor(Type type, UUID uuid, String author);

    /**
     * Method to get page of events(film)
     * @param type Film type
     * @param uuid Current film's uuid
     * @param status Current status
     * @return An event(film) that is found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND e.uuid = ?2 AND e.status = ?3")
    Optional<EventFilm> findByFilmTypeAndUuidAndStatus(Type type, UUID uuid, Status status);

    /**
     * Method to get page of events(film)
     * @param type Film type
     * @param uuid Current film's uuid
     * @param status Current status
     * @param author Current author
     * @return An event(film) that is found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND e.uuid = ?2 AND (e.status = ?3 OR e.author = ?4)")
    Optional<EventFilm> findByFilmTypeAndUuidAndStatusOrAuthor(Type type, UUID uuid, Status status, String author);

    /**
     * Method to get page of events(film)
     * @param type Film type
     * @param pageable Current page
     * @return Current page of events(film) that are found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 order by e.film.title")
    Page<EventFilm> findAllFilms(Type type, Pageable pageable);

    /**
     * Method to get page of events(film)
     * @param type Film type
     * @param status Current status
     * @param pageable Current page
     * @return Current page of events(film) that are found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND e.status = ?2 order by e.film.title")
    Page<EventFilm> findByFilmTypeAndStatus(Type type, Status status, Pageable pageable);

    /**
     * Method to get page of events(film)
     * @param type Film type
     * @param status Current status
     * @param author Current author
     * @param pageable Current page
     * @return Current page of events(film) that are found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = ?1 AND (e.status = ?2 OR e.author = ?3) order by e.film.title")
    Page<EventFilm> findByStatusOrAuthor(Type type, Status status, String author, Pageable pageable);


}