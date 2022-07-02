package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEventDao extends JpaRepository<Event, UUID> {

    @Query
    List<Event> findAllByActionType(Type type);
}
