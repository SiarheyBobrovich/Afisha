package by.it_academy.afisha.services.api;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.api.IEventConcertDto;
import by.it_academy.afisha.dto.api.IEventDto;
import by.it_academy.afisha.dto.api.IEventFilmDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IAfishaService {

    void save(@Valid IEventDto newEventDto, Type type);

    void update(@Valid IEventDto updateEventDto, Type type, UUID uuid, LocalDateTime dtUpdate);

    Page<EventFilm> getFilmEvents(Type type, Pageable pageable);

    Page<EventConcert> getConcertEvents(Type type, Pageable pageable);

}
