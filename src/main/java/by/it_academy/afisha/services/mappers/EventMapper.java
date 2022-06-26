package by.it_academy.afisha.services.mappers;

import by.it_academy.afisha.dao.entity.*;
import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventDto;

import java.time.LocalDateTime;

public class EventMapper {

    public FilmEvent getFilmEvent(EventDto dto) {
        FilmEvent filmEvent = FilmEvent.create()
                .setDtEvent(dto.getDtEvent())
                .setDtEndOfState(dto.getDtEndOfSale())
                .setStatus(dto.getStatus())
                .setDtCreate(LocalDateTime.now())
                .setCurrency(dto.getCurrency())
                .build();

        filmEvent.setDtUpdate(filmEvent.getDtCreate());

        Film film = new Film();
        film.setDescription(dto.getDescription());
        film.setTitle(dto.getTitle());

        filmEvent.setFilm(film);

        return filmEvent;
    }

    public ConcertEvent getConcertEvent(EventDto dto) {
        ConcertEvent concertEvent = ConcertEvent.create()
                .setDtEvent(dto.getDtEvent())
                .setDtEndOfState(dto.getDtEndOfSale())
                .setStatus(dto.getStatus())
                .setDtCreate(LocalDateTime.now())
                .setCurrency(dto.getCurrency())
                .build();

        concertEvent.setDtUpdate(concertEvent.getDtCreate());

        Concert concert = new Concert();
        concert.setDescription(dto.getDescription());
        concert.setTitle(dto.getTitle());

        concertEvent.setConcert(concert);

        return concertEvent;
    }

    public AbstractEvent getAbstractEvent(EventDto dto, Type type) {
        if (Type.FILMS.equals(type)) {

            FilmEvent filmEvent = FilmEvent.create()
                    .setDtEvent(dto.getDtEvent())
                    .setDtEndOfState(dto.getDtEndOfSale())
                    .setStatus(dto.getStatus())
                    .setDtCreate(LocalDateTime.now())
                    .setCurrency(dto.getCurrency())
                    .build();

            filmEvent.setDtUpdate(filmEvent.getDtCreate());

            Film film = new Film();
            film.setDescription(dto.getDescription());
            film.setTitle(dto.getTitle());

            filmEvent.setFilm(film);

            return filmEvent;

        }else if (Type.CONCERTS.equals(type)) {
            ConcertEvent concertEvent = ConcertEvent.create()
                    .setDtEvent(dto.getDtEvent())
                    .setDtEndOfState(dto.getDtEndOfSale())
                    .setStatus(dto.getStatus())
                    .setDtCreate(LocalDateTime.now())
                    .setCurrency(dto.getCurrency())
                    .build();

            concertEvent.setDtUpdate(concertEvent.getDtCreate());

            Concert concert = new Concert();
            concert.setDescription(dto.getDescription());
            concert.setTitle(dto.getTitle());

            concertEvent.setConcert(concert);

            return concertEvent;

        }else {
            throw new IllegalArgumentException();
        }
    }
}
