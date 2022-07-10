package by.it_academy.afisha.controllers.converters;

import by.it_academy.afisha.dao.entity.actions.Film;
import by.it_academy.afisha.dao.entity.events.EventFilm;
import by.it_academy.afisha.dto.ClassifiersPage;
import by.it_academy.afisha.dto.PageEventDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageEventFilmxToPageEventDtoConverter implements Converter<Page<EventFilm>, ClassifiersPage<PageEventDto>> {

    @Override
    public ClassifiersPage<PageEventDto> convert(Page<EventFilm> source) {
        return new ClassifiersPage<>(

                source.getTotalPages(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.get().map(x -> {
                    Film film = x.getAction();
                    return new PageEventDto(
                            film.getTitle(),
                            film.getDescription(),
                            x.getDtEvent(),
                            x.getDtEndOfSale(),
                            x.getStatus(),
                            x.getUuid(),
                            x.getDtCreate(),
                            x.getDtUpdate(),
                            film.getType()
                    );
                }).collect(Collectors.toList())
        );
    }
}
