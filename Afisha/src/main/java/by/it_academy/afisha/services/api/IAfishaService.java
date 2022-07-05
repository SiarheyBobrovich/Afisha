package by.it_academy.afisha.services.api;

import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAfishaService {

    void save(EventDto event, Type type);

    void update(EventDto event, Type type, UUID uuid, LocalDateTime dtUpdate);

    Page<Event> getEvents(Type type, Pageable pageable);

}
