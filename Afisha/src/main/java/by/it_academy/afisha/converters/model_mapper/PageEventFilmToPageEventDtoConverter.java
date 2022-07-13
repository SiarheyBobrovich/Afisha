package by.it_academy.afisha.converters.model_mapper;

import by.it_academy.afisha.dao.entity.actions.Film;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.PageEventDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PageEventFilmToPageEventDtoConverter implements Converter<EventFilm, PageEventDto> {

    @Override
    public PageEventDto convert(MappingContext<EventFilm, PageEventDto> context) {
        EventFilm event = context.getSource();
        Film film = event.getAction();

        return new PageEventDto(
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