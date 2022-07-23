package by.it_academy.afisha.services.api;

import by.it_academy.afisha.dto.*;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * An afisha's event service interface
 */
public interface IAfishaService {

    /**
     * Convert Event(film)-dto to Event(film)-entity and save in dao
     * @param newEventDto Event to save
     * @param author Username who submitted the request
     * @throws ConstraintViolationException If class newEventDto is invalid
     */
    void save(@Valid EventFilmDto newEventDto,
              String author) throws ConstraintViolationException;

    /**
     * Convert Event(concert)-dto to Event(concert)-entity and save in dao
     * @param newEventDto Event to save
     * @param author Username who submitted the request
     * @throws ConstraintViolationException If class newEventDto is invalid
     */
    void save(@Valid EventConcertDto newEventDto,
              String author) throws ConstraintViolationException;

    /**
     * Update current Event(film) and save in dao
     * @param updateEventDto Dto with updatable params
     * @param uuid UUID of the Event(film) to be updated
     * @param dtUpdate Version of the Event(film) for optimistic locks
     * @param author Username who submitted the request
     * @throws ConstraintViolationException If class updateEventDto is invalid
     */
    void update(@Valid EventFilmDto updateEventDto,
                UUID uuid,
                LocalDateTime dtUpdate,
                String author) throws ConstraintViolationException;

    /**
     * Update current Event(concert) and save in dao
     * @param updateEventDto Dto with updatable params
     * @param uuid UUID of the Event(concert) to be updated
     * @param dtUpdate Version of the event (concert) for optimistic locks
     * @param author Username who submitted the request
     * @throws ConstraintViolationException If class updateEventDto is invalid
     */
    void update(@Valid EventConcertDto updateEventDto,
                UUID uuid,
                LocalDateTime dtUpdate,
                String author) throws ConstraintViolationException;

    /**
     * Method to get page of Events(film) from database
     * @param pageable Page to get
     * @return Current page from database
     */
    PageDtos<PageEventDto> getEventFilms(Pageable pageable);

    /**
     * Method to get page of Events(concert) from database
     * @param pageable page to get
     * @return Current page from database
     */
    PageDtos<PageEventDto> getEventConcerts(Pageable pageable);

    /**
     * Method to get an Event(film) from database
     * @param uuid Film's uuid
     * @return Current Event(film) from database
     */
    PageEventDto getSingleEventFilm(UUID uuid);

    /**
     * Method to get an Event(concert) from database
     * @param uuid Film's uuid
     * @return Current Event(concert) from database
     */
    PageEventDto getSingleEventConcert(UUID uuid);
}
