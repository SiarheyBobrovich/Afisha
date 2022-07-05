package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventDao extends JpaRepository<Event, UUID> {

    @Query
    Page<Event> findAllByActionType(@Param("type")Type type,
                                    Pageable pageable);
}
