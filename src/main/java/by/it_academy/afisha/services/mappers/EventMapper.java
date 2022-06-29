package by.it_academy.afisha.services.mappers;

import by.it_academy.afisha.dao.entity.*;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventMapper {

    public FilmEvent getFilmEvent(EventDto dto) {
        FilmEvent filmEvent = FilmEvent.create()
                .setDtEvent(dto.getDtEvent())
                .setDtEndOfState(dto.getDtEndOfSale())
                .setStatus(dto.getStatus())
                .setDtCreate(LocalDateTime.now())
                .setUuid(UUID.randomUUID())
                .build();

        filmEvent.setDtUpdate(filmEvent.getDtCreate());

        Film film = new Film();
        film.setUuid(UUID.randomUUID());
        film.setDescription(dto.getDescription());
        film.setTitle(dto.getTitle());

        filmEvent.setFilm(film);

        return filmEvent;
    }

    public ConcertEvent getConcertEvent(EventDto dto) {
        ConcertEvent concertEvent = ConcertEvent.create()
                .setUuid(UUID.randomUUID())
                .setDtEvent(dto.getDtEvent())
                .setDtEndOfState(dto.getDtEndOfSale())
                .setStatus(dto.getStatus())
                .setDtCreate(LocalDateTime.now())
                .build();

        concertEvent.setDtUpdate(concertEvent.getDtCreate());

        Concert concert = new Concert();
        concert.setUuid(UUID.randomUUID());
        concert.setDescription(dto.getDescription());
        concert.setTitle(dto.getTitle());


        concertEvent.setConcert(concert);

        return concertEvent;
    }

    public AbstractEvent getAbstractEvent(EventDto dto, Type type) {
        if (Type.FILMS.equals(type)) {

            FilmEvent filmEvent = FilmEvent.create()
                    .setUuid(UUID.randomUUID())
                    .setDtEvent(dto.getDtEvent())
                    .setDtEndOfState(dto.getDtEndOfSale())
                    .setStatus(dto.getStatus())
                    .setDtCreate(LocalDateTime.now())
                    .build();

            filmEvent.setDtUpdate(filmEvent.getDtCreate());

            Film film = new Film();
            film.setUuid(UUID.randomUUID());
            film.setDescription(dto.getDescription());
            film.setTitle(dto.getTitle());

            filmEvent.setFilm(film);

            return filmEvent;

        }else if (Type.CONCERTS.equals(type)) {
            ConcertEvent concertEvent = ConcertEvent.create()
                    .setUuid(UUID.randomUUID())
                    .setDtEvent(dto.getDtEvent())
                    .setDtEndOfState(dto.getDtEndOfSale())
                    .setStatus(dto.getStatus())
                    .setDtCreate(LocalDateTime.now())
                    .build();

            concertEvent.setDtUpdate(concertEvent.getDtCreate());

            Concert concert = new Concert();
            concert.setUuid(UUID.randomUUID());
            concert.setDescription(dto.getDescription());
            concert.setTitle(dto.getTitle());

            concertEvent.setConcert(concert);

            return concertEvent;

        }else {
            throw new IllegalArgumentException();
        }
    }
}
