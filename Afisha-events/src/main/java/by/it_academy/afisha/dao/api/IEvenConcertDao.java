package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEvenConcertDao extends JpaRepository<EventConcert, UUID> {

    /**
     * Method to get page of Events(concert)
     * @param type Type of concert
     * @param uuid Current concert's uuid
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = ?1 AND e.uuid = ?2")
    Optional<EventConcert> findByUuid(Type type, UUID uuid);

    /**
     * Method to get page of Events(concert)
     * @param type Type of concert
     * @param uuid Current concert's uuid
     * @param author Current author
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = ?1 AND e.uuid = ?2 AND e.author = ?3")
    Optional<EventConcert> findByUuidAndAuthor(Type type, UUID uuid, String author);

    /**
     * Method to get page of Events(concert)
     * @param uuid Current concert's uuid
     * @param type Type of concert
     * @param status Current concert's status
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.uuid = ?1 AND e.concert.type = ?2 AND e.status = ?3")
    Optional<EventConcert> findByUuidAndAndStatus(UUID uuid, Type type, Status status);

    /**
     * Method to get page of Events(concert)
     * @param uuid Current concert's uuid
     * @param type Type of concert
     * @param status Current concert's status
     * @param author Current author
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.uuid = ?1 AND e.concert.type = ?2 AND (e.status = ?3 or e.author = ?4)")
    Optional<EventConcert> findByUuidAndStatusOrAuthor(UUID uuid, Type type, Status status, String author);

    /**
     * Method to get page of Events(concert)
     * @param type Type of concert
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = ?1 ORDER BY e.concert.title")
    Page<EventConcert> findAllConcertsType(Type type, Pageable pageable);

    /**
     * Method to get page of Events(concert)
     * @param type Type of concert
     * @param status Current concert's status
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = ?1 AND e.status = ?2 ORDER BY e.concert.title")
    Page<EventConcert> findByConcertTypeAndStatus(Type type, Status status, Pageable pageable);

    /**
     * Method to get page of Events(concert)
     * @param status Current concert's status
     * @param author Current author
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = ?1 AND (e.status = ?2 OR e.author = ?3) ORDER BY e.concert.title")
    Page<EventConcert> findByConcertTypeAndStatusAndAuthor(Type type, Status status, String author, Pageable pageable);
}
