package by.it_academy.afisha.services.mappers;

import by.it_academy.afisha.dao.entity.Action;
import by.it_academy.afisha.dao.entity.Event;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventMapper {

    public Event getEvent(EventDto dto, Type type) {
        Action action = new Action();
        action.setUuid(UUID.randomUUID());
        action.setType(type);
        action.setTitle(dto.getTitle());
        action.setDescription(dto.getDescription());

        Event event = Event.create()
                .setUuid(UUID.randomUUID())
                .setStatus(dto.getStatus())
                .setDtCreate(LocalDateTime.now())
                .setAction(action)
                .setDtEvent(dto.getDtEvent())
                .setDtEndOfSale(dto.getDtEndOfSale())
                .build();

        event.setDtUpdate(event.getDtCreate());

        return event;
    }
}
