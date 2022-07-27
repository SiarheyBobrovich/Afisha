package by.it_academy.afisha.converters.model_mapper;

import by.it_academy.afisha.dao.entity.actions.Concert;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dto.ResponseEventDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Converter from EventConcert(entity) class to PageEventDto class
 */
@Component
public class EventConcertToResponseEventDtoConverter implements Converter<EventConcert, ResponseEventDto> {

    @Override
    public ResponseEventDto convert(MappingContext<EventConcert, ResponseEventDto> context) {
        EventConcert event = context.getSource();
        Concert concert = event.getAction();

        return new ResponseEventDto(
                concert.getTitle(),
                concert.getDescription(),
                event.getDtEvent(),
                event.getDtEndOfSale(),
                event.getStatus(),
                event.getUuid(),
                event.getDtCreate(),
                event.getDtUpdate(),
                concert.getType()
        );
    }
}