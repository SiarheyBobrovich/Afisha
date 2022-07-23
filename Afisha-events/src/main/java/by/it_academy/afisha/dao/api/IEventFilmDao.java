package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventFilmDao extends JpaRepository<EventFilm, UUID> {

    /**
     * Method to get page of events(film)
     * @param uuid Current film's uuid
     * @return An event(film) that is found
     */
    @Query("select e from EventFilm e where e.film.type = 'FILMS' and e.uuid = ?1")
    Optional<EventFilm> findByFilmTypeAndUuid(UUID uuid);

    /**
     * Method to get page of event(film)
     * @param uuid Current film's uuid
     * @param author Current author
     * @return An event(film) that is found
     */
    @Query("select e from EventFilm e where e.film.type = 'FILMS' and e.uuid = ?1 and e.author = ?2")
    Optional<EventFilm> findByUuidAndAuthor(UUID uuid, String author);


    /**
     * Method to get page of events(film)
     * @param uuid Current film's uuid
     * @param status Current status
     * @return An event(film) that is found
     */
    @Query("select e from EventFilm e where e.film.type = 'FILMS' and e.uuid = ?1 and e.status = ?2")
    Optional<EventFilm> findByFilmTypeAndUuidAndStatus(UUID uuid, Status status);

    /**
     * Method to get page of events(film)
     * @param uuid Current film's uuid
     * @param status Current status
     * @param author Current author
     * @return An event(film) that is found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.uuid = ?1 AND e.film.type = 'FILMS' AND (e.status = ?2 OR e.author = ?3)")
    Optional<EventFilm> findByUuidAndFilmTypeAndStatusOrAuthor(UUID uuid, Status status, String author);

    /**
     * Method to get page of events(film)
     * @param pageable Current page
     * @return Current page of events(film) that are found
     */
    @Query("select e from EventFilm e where e.film.type = 'FILMS'")
    Page<EventFilm> findAllFilms(Pageable pageable);

    /**
     * Method to get page of events(film)
     * @param status Current status
     * @param pageable Current page
     * @return Current page of events(film) that are found
     */
    @Query("select e from EventFilm e where e.film.type = 'FILMS' and e.status = ?1")
    Page<EventFilm> findByStatus(Status status, Pageable pageable);

    /**
     * Method to get page of events(film)
     * @param status Current status
     * @param author Current author
     * @param pageable Current page
     * @return Current page of events(film) that are found
     */
    @Query("SELECT e FROM EventFilm e WHERE e.film.type = 'FILMS' AND (e.status = ?1 OR e.author = ?2)")
    Page<EventFilm> findByStatusOrAuthor(Status status, String author, Pageable pageable);


}