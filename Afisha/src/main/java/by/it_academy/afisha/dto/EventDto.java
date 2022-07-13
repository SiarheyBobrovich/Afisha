package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EventFilmDto.class, name = "FILMS"),
        @JsonSubTypes.Type(value = EventConcertDto.class, name = "CONCERTS")
})
public abstract class EventDto {

    @NotNull
    @Pattern(regexp = "[\\p{Alpha}\\p{Digit}\\p{Punct}\\p{Blank}]++",
            message = "не верно введён титульный лист")
    private final String title;

    @NotNull
    @Pattern(regexp = "[\\p{Alpha}\\p{Digit}\\p{Punct}\\p{Blank}]++",
            message = "не верно введено описание")
    private final String description;

    @NotNull(message = "введите дату мероприятия")
    private final LocalDateTime dtEvent;

    @NotNull
    private final LocalDateTime dtEndOfSale;

    @NotNull(message = "Не верно введён статус")
    private final Status status;

    protected EventDto(String title,
                    String description,
                    LocalDateTime dtEvent,
                    LocalDateTime dtEndOfSale,
                    Status status) {
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public Status getStatus() {
        return status;
    }

    public abstract Type getType();

}
