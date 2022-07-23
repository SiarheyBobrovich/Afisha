package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Status;
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
     * @param uuid Current concert's uuid
     * @return Page of Events(concert) that are found
     */
    @Query("select e from EventConcert e where e.uuid = ?1 and e.concert.type = 'CONCERTS'")
    Optional<EventConcert> findByUuid(UUID uuid);

    /**
     * Method to get page of Events(concert)
     * @param uuid Current concert's uuid
     * @param author Current author
     * @return Page of Events(concert) that are found
     */
    @Query("select e from EventConcert e where e.concert.type = 'CONCERTS' and e.uuid = ?1 and e.author = ?2")
    Optional<EventConcert> findByUuidAndAuthor(UUID uuid, String author);

    /**
     * Method to get page of Events(concert)
     * @param uuid Current concert's uuid
     * @param status Current concert's status
     * @return Page of Events(concert) that are found
     */
    @Query("select e from EventConcert e where e.uuid = ?1 and e.concert.type = 'CONCERTS' and e.status = ?2")
    Optional<EventConcert> findByUuidAndAndStatus(UUID uuid, Status status);

    /**
     * Method to get page of Events(concert)
     * @param uuid Current concert's uuid
     * @param status Current concert's status
     * @param author Current author
     * @return Page of Events(concert) that are found
     */
    @Query("select e from EventConcert e where e.uuid = ?1 and e.concert.type = 'CONCERTS' and (e.status = ?2 or e.author = ?3)")
    Optional<EventConcert> findByUuidAndStatusOrAuthor(UUID uuid, Status status, String author);

    /**
     * Method to get page of Events(concert)
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query("select e from EventConcert e where e.concert.type = 'CONCERTS'")
    Page<EventConcert> findAllConcers(Pageable pageable);

    /**
     * Method to get page of Events(concert)
     * @param status Current concert's status
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query("select e from EventConcert e where e.concert.type = 'CONCERTS' and e.status = ?1")
    Page<EventConcert> findByConcertTypeAndStatus(Status status, Pageable pageable);

    /**
     * Method to get page of Events(concert)
     * @param status Current concert's status
     * @param author Current author
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = 'CONCERTS' AND (e.status = ?1 OR e.author = ?2)")
    Page<EventConcert> findByConcertTypeAndStatusAndAuthor(Status status, String author, Pageable pageable);
}
