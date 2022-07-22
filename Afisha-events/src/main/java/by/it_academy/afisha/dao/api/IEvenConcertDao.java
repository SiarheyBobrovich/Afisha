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
     * @param type Action's type parameter
     * @param pageable Current page
     * @return Page of Events(concert) that are found
     */
    @Query
    Page<EventConcert> findAllByConcertType(Type type, Pageable pageable);

    @Query("select e from EventConcert e where e.uuid = ?1 and e.author = ?2")
    Optional<EventConcert> findByUuidAndAuthor(UUID uuid, String author);
    @Query
    Page<EventConcert> findByConcertTypeAndStatus(Type type, Status status, Pageable pageable);

    @Query("SELECT e FROM EventConcert e WHERE e.concert.type = ?1 AND (e.status = ?2 OR e.author = ?3)")
    Page<EventConcert> findByConcertTypeAndStatusAndAuthor(Type type, Status status, String author, Pageable pageable);

}
