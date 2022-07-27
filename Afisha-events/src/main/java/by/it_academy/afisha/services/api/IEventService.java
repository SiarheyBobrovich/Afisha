package by.it_academy.afisha.services.api;

import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dao.entity.events.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * An afisha's event service interface
 * @param <D> Argument object
 * @param <E> Returned object
 */
public interface IEventService<D, E> {

    /**
     * Convert Event(T)-dto to Event(T)-entity and save in dao
     * @param newEvent Event to save
     * @param author Username who submitted the request
     * @throws ConstraintViolationException If class newEventDto is invalid
     */
    void save(@Valid D newEvent,
              String author) throws ConstraintViolationException;

    /**
     * Update current Event(film) and save in dao
     * @param updateEvent Dto with updatable params
     * @param uuid UUID of the Event(film) to be updated
     * @param dtUpdate Version of the Event(film) for optimistic locks
     * @param author Username who submitted the request
     * @throws ConstraintViolationException If class updateEventDto is invalid
     */
    void update(@Valid D updateEvent,
                UUID uuid,
                LocalDateTime dtUpdate,
                String author) throws ConstraintViolationException;

    /**
     * Method to get page of Events(film) from database
     * @param pageable Page to get
     * @return Current page from database
     */
    Page<E> getAllEvents(Type type, Pageable pageable);

    /**
     * Method to get an Event(film) from database
     * @param type Type object
     * @param uuid Film's uuid
     * @return Current Event(film) from database
     */
    E getSingleEvent(Type type, UUID uuid);

    /**
     * Method for generating UUID and date for new entities
     * @param event a new entities
     */
    default void setDefaultFields(Event event) {
        event.setUuid(UUID.randomUUID());
        event.getAction().setUuid(UUID.randomUUID());
        event.setDtCreate(LocalDateTime.now());
        event.setDtUpdate(event.getDtCreate());
    }

}
