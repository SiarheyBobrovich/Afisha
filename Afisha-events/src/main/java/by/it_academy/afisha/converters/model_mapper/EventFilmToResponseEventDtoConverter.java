package by.it_academy.afisha.converters.model_mapper;

import by.it_academy.afisha.dao.entity.actions.Film;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.ResponseEventDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Converter from EventFilm(entity) class to PageEventDto class
 */
@Component
public class EventFilmToResponseEventDtoConverter implements Converter<EventFilm, ResponseEventDto> {

    @Override
    public ResponseEventDto convert(MappingContext<EventFilm, ResponseEventDto> context) {
        EventFilm event = context.getSource();
        Film film = event.getAction();

        return new ResponseEventDto(
                film.getTitle(),
                film.getDescription(),
                event.getDtEvent(),
                event.getDtEndOfSale(),
                event.getStatus(),
                event.getUuid(),
                event.getDtCreate(),
                event.getDtUpdate(),
                film.getType()
        );
    }
}