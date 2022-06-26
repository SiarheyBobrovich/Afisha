package by.it_academy.afisha.services.api;

import by.it_academy.afisha.dao.entity.AbstractEvent;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IAfishaService {

    void save(EventDto event, Type type);

    void update(EventDto event, Type type, UUID uuid, LocalDateTime dtUpdate);

    List<AbstractEvent> getEvents(Type type);

}
