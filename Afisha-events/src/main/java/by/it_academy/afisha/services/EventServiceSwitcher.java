package by.it_academy.afisha.services;

import by.it_academy.afisha.dao.entity.actions.Concert;
import by.it_academy.afisha.dao.entity.actions.Film;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.*;
import by.it_academy.afisha.exceptions.TypeNotImplementedException;
import by.it_academy.afisha.services.api.IEventService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventServiceSwitcher implements IEventService<RequestEventDto, ResponseEventDto> {

    private final IEventService<EventConcertDto, EventConcert> concertService;
    private final IEventService<EventFilmDto, EventFilm> filmService;

    private final ModelMapper mapper;

    public EventServiceSwitcher(IEventService<EventConcertDto, EventConcert> concertService,
                                IEventService<EventFilmDto, EventFilm> filmService,
                                ModelMapper mapper) {
        this.concertService = concertService;
        this.filmService = filmService;
        this.mapper = mapper;
    }

    @Override
    public void save(RequestEventDto newEvent, String author) throws ConstraintViolationException {
        switch (newEvent.getType()) {
            case FILMS:
                filmService.save((EventFilmDto) newEvent, author);
                break;

            case CONCERTS:
                concertService.save((EventConcertDto) newEvent, author);
                break;

            default:
                throw new TypeNotImplementedException();
        }
    }

    @Override
    public void update(RequestEventDto updateEvent, UUID uuid, LocalDateTime dtUpdate, String author) throws ConstraintViolationException {
        switch (updateEvent.getType()) {

            case FILMS:
                filmService.update((EventFilmDto) updateEvent, uuid, dtUpdate, author);
                break;

            case CONCERTS:
                concertService.update((EventConcertDto) updateEvent, uuid, dtUpdate, author);
                break;

            default:
                throw new TypeNotImplementedException();
        }
    }

    @Override
    public Page<ResponseEventDto> getAllEvents(Type type, Pageable pageable) {
        Page<ResponseEventDto> allEvents;

        switch (type) {

            case FILMS:
                allEvents = filmService.getAllEvents(type, pageable)
                        .map(eventFilm -> mapper.map(eventFilm, ResponseEventDto.class));
                break;

            case CONCERTS:
                allEvents = concertService.getAllEvents(type, pageable)
                        .map(concert -> mapper.map(concert, ResponseEventDto.class));
                break;

            default:
                throw new TypeNotImplementedException();
        }

        return allEvents;
    }

    @Override
    public ResponseEventDto getSingleEvent(Type type, UUID uuid) {

        ResponseEventDto event;

        switch (type) {

            case FILMS:
                event = mapper.map(filmService.getSingleEvent(type, uuid), ResponseEventDto.class);
                break;

            case CONCERTS:
                event = mapper.map(concertService.getSingleEvent(type, uuid), ResponseEventDto.class);
                break;

            default:
                throw new TypeNotImplementedException();
        }

        return event;
    }

}
