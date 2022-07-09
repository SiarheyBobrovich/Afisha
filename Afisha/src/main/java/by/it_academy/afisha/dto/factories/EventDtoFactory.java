package by.it_academy.afisha.dto.factories;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import by.it_academy.afisha.dto.EventConcertDto;
import by.it_academy.afisha.dto.EventFilmDto;
import by.it_academy.afisha.dto.api.IEventDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class EventDtoFactory {

    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private Status status;
    private UUID country;
    private Integer releaseYear;

    private Type type;

    @JsonFormat(pattern = "dd MMMM yyyy")
    private LocalDate releaseDate;
    private Integer duration;
    private UUID category;

    public EventDtoFactory() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public IEventDto getDto() {
        final IEventDto eventDto;

        if (Type.CONCERTS.equals(type)) {
            eventDto = new EventConcertDto(
                    title, description, dtEvent, dtEndOfSale, status, category
            );

        } else if (Type.FILMS.equals(type)){
            eventDto = new EventFilmDto(
                    title, description, dtEvent, dtEndOfSale, status, country, releaseYear, releaseDate, duration
            );

        } else {
            throw new IllegalArgumentException("Тип: " + type + " не обслуживается");
        }

        return eventDto;
    }

}
