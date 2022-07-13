package by.it_academy.afisha.services.api;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IAfishaService {

    /**
     * Convert Event(film)-dto to Event(film)-entity and save in dao
     * @param newEventDto - Event to save
     */
    void save(@Valid EventFilmDto newEventDto);

    /**
     * Convert Event(concert)-dto to Event(concert)-entity and save in dao
     * @param newEventDto - Event to save
     */
    void save(@Valid EventConcertDto newEventDto);

    /**
     * Update current Event(film) and save in dao
     * @param updateEventDto - dto with updatable params
     * @param uuid - UUID of the Event(film) to be updated
     * @param dtUpdate - Version of the Event(film) for optimistic locks
     */
    void update(@Valid EventFilmDto updateEventDto, UUID uuid, LocalDateTime dtUpdate);

    /**
     * Update current Event(concert) and save in dao
     * @param updateEventDto - dto with updatable params
     * @param uuid - UUID of the Event(concert) to be updated
     * @param dtUpdate - Version of the event (concert) for optimistic locks
     */
    void update(@Valid EventConcertDto updateEventDto, UUID uuid, LocalDateTime dtUpdate);

    PageDtos<PageEventDto> getFilmEvents(Type type, Pageable pageable);

    PageDtos<PageEventDto> getConcertEvents(Type type, Pageable pageable);

}
