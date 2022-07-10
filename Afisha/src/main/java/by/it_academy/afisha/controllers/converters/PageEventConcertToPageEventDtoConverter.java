package by.it_academy.afisha.controllers.converters;

import by.it_academy.afisha.dao.entity.actions.Concert;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dto.ClassifiersPage;
import by.it_academy.afisha.dto.PageEventDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PageEventConcertToPageEventDtoConverter implements Converter<Page<EventConcert>, ClassifiersPage<PageEventDto>> {

    @Override
    public ClassifiersPage<PageEventDto> convert(Page<EventConcert> source) {
        return new ClassifiersPage<>(

                source.getTotalPages(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.get().map(x -> {
                    Concert concert = x.getAction();

                    return new PageEventDto(
                            concert.getTitle(),
                            concert.getDescription(),
                            x.getDtEvent(),
                            x.getDtEndOfSale(),
                            x.getStatus(),
                            x.getUuid(),
                            x.getDtCreate(),
                            x.getDtUpdate(),
                            concert.getType()
                    );
                }).collect(Collectors.toList())
        );
    }
}
