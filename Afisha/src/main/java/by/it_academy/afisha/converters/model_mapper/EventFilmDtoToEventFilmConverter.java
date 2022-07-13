package by.it_academy.afisha.converters.model_mapper;

import by.it_academy.afisha.dao.entity.actions.Film;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.EventFilmDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Converter from EventFilmDto class to EventFilm(entity) class
 */
@Component
public class EventFilmDtoToEventFilmConverter implements Converter<EventFilmDto, EventFilm> {

    @Override
    public EventFilm convert(MappingContext<EventFilmDto, EventFilm> context) {
        EventFilm eventFilm = context.getDestination();

        if (eventFilm == null) {
            eventFilm = new EventFilm();
            eventFilm.setFilm(new Film());
        }

        EventFilmDto source = context.getSource();

        eventFilm.setDtEvent(source.getDtEvent());
        eventFilm.setDtEndOfSale(source.getDtEndOfSale());
        eventFilm.setStatus(source.getStatus());
        eventFilm.getAction().setTitle(source.getTitle());
        eventFilm.getAction().setDescription(source.getDescription());
        eventFilm.getAction().setReleaseDate(source.getReleaseDate());
        eventFilm.getAction().setReleaseYear(source.getReleaseYear());
        eventFilm.getAction().setCountry(source.getCountry());
        eventFilm.getAction().setDuration(source.getDuration());

        return eventFilm;
    }
}
