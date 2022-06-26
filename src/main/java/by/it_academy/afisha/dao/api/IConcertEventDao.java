package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.ConcertEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IConcertEventDao extends JpaRepository<ConcertEvent, UUID> {
}
