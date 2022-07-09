package by.it_academy.afisha.dto.factories;

import by.it_academy.afisha.dto.EventConcertDto;
import by.it_academy.afisha.dto.EventFilmDto;
import by.it_academy.afisha.dto.api.IEventDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class SpringRequestDtoFactory implements ConverterFactory<EventDtoFactory, IEventDto> {

    @Override
    public <T extends IEventDto> Converter<EventDtoFactory, T> getConverter(Class<T> targetType) {
        return source -> {
            final IEventDto eventDto;

            if (source.getCategory() != null) {
                eventDto = new EventConcertDto(
                        source.getTitle(),
                        source.getDescription(),
                        source.getDtEvent(),
                        source.getDtEndOfSale(),
                        source.getStatus(),
                        source.getCategory()
                );

            } else {
                eventDto = new EventFilmDto(
                        source.getTitle(),
                        source.getDescription(),
                        source.getDtEvent(),
                        source.getDtEndOfSale(),
                        source.getStatus(),
                        source.getCountry(),
                        source.getReleaseYear(),
                        source.getReleaseDate(),
                        source.getDuration()
                );
            }

            return (T) eventDto;
        };
    }
}
