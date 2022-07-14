package by.it_academy.afisha.converters.model_mapper;

import by.it_academy.afisha.dao.entity.actions.Concert;
import by.it_academy.afisha.dao.entity.events.EventConcert;
import by.it_academy.afisha.dto.EventConcertDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Converter from EventConcertDto class to EventConcert(entity) class
 */
@Component
public class EventConcertDtoToEventConcertConverter implements Converter<EventConcertDto, EventConcert> {

    @Override
    public EventConcert convert(MappingContext<EventConcertDto, EventConcert> context) {
        EventConcert eventConcert = context.getDestination();

        if (eventConcert == null) {
            eventConcert = new EventConcert();
            eventConcert.setConcert(new Concert());
        }

        EventConcertDto source = context.getSource();

        eventConcert.setDtEvent(source.getDtEvent());
        eventConcert.setDtEndOfSale(source.getDtEndOfSale());
        eventConcert.setStatus(source.getStatus());

        Concert concert = eventConcert.getAction();

        concert.setTitle(source.getTitle());
        concert.setDescription(source.getDescription());
        concert.setCategory(source.getCategory());

        return eventConcert;
    }
}
