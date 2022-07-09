package by.it_academy.afisha.services.mappers;

import by.it_academy.afisha.dao.entity.actions.Concert;
import by.it_academy.afisha.dao.entity.actions.Film;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.EventDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventMapper {

//
//    public EventFilm getEventFilm(EventDto dto) {
//
//        EventFilm event = getEventFilmWithoutUuid(dto);
//            event.setUuid(UUID.randomUUID());
//            event.setFilm(getFilm(dto));
//
//        return event;
//    }
//
//    public EventFilm getEventFilmWithoutUuid(EventDto dto) {
//        EventFilm event = new EventFilm();
//            event.setStatus(dto.getStatus());
//            event.setDtCreate(LocalDateTime.now());
//            event.setDtUpdate(event.getDtCreate());
//            event.setDtEvent(dto.getDtEvent());
//            event.setDtEndOfSale(dto.getDtEndOfSale());
//
//        return event;
//    }
//
//
//    public Film getFilm(EventDto dto) {
//        Film film = getFilmWithoutUuid(dto);
//            film.setUuid(UUID.randomUUID());
//
//        return film;
//    }
//
//    public Film getFilmWithoutUuid(EventDto dto) {
//        Film film = new Film();
//            film.setTitle(dto.getTitle());
//            film.setDescription(dto.getDescription());
//            film.setCountry(dto.getCountry());
//            film.setReleaseYear(dto.getReleaseYear());
//            film.setReleaseDate(dto.getReleaseDate());
//            film.setDuration(dto.getDuration());
//
//        return film;
//    }
//
//    public EventConcert getEventConcert(EventDto dto) {
//        EventConcert eventConcert = getEventConcertWithoutUuid(dto);
//            eventConcert.setUuid(UUID.randomUUID());
//            eventConcert.setConcert(getConcert(dto));
//
//        return eventConcert;
//    }
//
//    public EventConcert getEventConcertWithoutUuid(EventDto dto) {
//
//        EventConcert event = new EventConcert();
//            event.setStatus(dto.getStatus());
//            event.setDtCreate(LocalDateTime.now());
//            event.setDtUpdate(event.getDtCreate());
//            event.setDtEvent(dto.getDtEvent());
//            event.setDtEndOfSale(dto.getDtEndOfSale());
//
//        return event;
//    }
//
//    public Concert getConcert(EventDto dto) {
//
//        Concert concert = getConcertWithoutUuid(dto);
//            concert.setUuid(UUID.randomUUID());
//
//        return concert;
//    }
//
//    public Concert getConcertWithoutUuid(EventDto dto) {
//
//        Concert concert = new Concert();
//            concert.setTitle(dto.getTitle());
//            concert.setDescription(dto.getDescription());
//            concert.setCategory(dto.getCategory());
//
//        return concert;
//    }
//
//    public EventFilm updateEventFilm(EventFilm oldEventFilm, EventDto dto) {
//        EventFilm updatedEventFilm = getEventFilmWithoutUuid(dto);
//            updatedEventFilm.setUuid(oldEventFilm.getUuid());
//
//        Film updatedFilm = getFilmWithoutUuid(dto);
//            updatedFilm.setUuid(oldEventFilm.getFilm().getUuid());
//
//        updatedEventFilm.setFilm(updatedFilm);
//
//        return updatedEventFilm;
//    }
//
//    public EventConcert updateEventConcert(EventConcert oldEventConcert, EventDto dto) {
//        EventConcert updatedEventConcert = getEventConcertWithoutUuid(dto);
//            updatedEventConcert.setUuid(oldEventConcert.getUuid());
//
//        Concert updatedConcert = getConcertWithoutUuid(dto);
//            updatedConcert.setUuid(oldEventConcert.getUuid());
//
//        updatedEventConcert.setConcert(updatedConcert);
//
//        return updatedEventConcert;
//    }
}
