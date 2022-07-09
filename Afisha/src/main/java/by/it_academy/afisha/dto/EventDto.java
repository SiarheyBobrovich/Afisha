package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dto.api.IEventDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Validated
public class EventDto implements IEventDto {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[\\p{Alpha}\\p{Digit}\\p{Punct}]++",
            message = "Не верно введён титульный лист")
    private final String title;

    @NotNull
    @Pattern(regexp = "[\\p{Alpha}\\p{Digit}\\p{Punct}]++",
            message = "Не верно введено описание")
    private final String description;

    @NotNull(message = "Введите дату мероприятия")
    private final LocalDateTime dtEvent;

    @NotNull(message = "Введите дату конца продажи билетов")
    private final LocalDateTime dtEndOfSale;

    @NotNull(message = "Не верно введён статус")
    private final Status status;

    public EventDto(String title,
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

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    @Override
    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    @Override
    public Status getStatus() {
        return status;
    }

}
