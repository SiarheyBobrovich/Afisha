package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEvenConcertDao extends JpaRepository<EventConcert, UUID> {

    @Query
    Page<EventConcert> findAllByConcertType(Type type, Pageable pageable);
}
