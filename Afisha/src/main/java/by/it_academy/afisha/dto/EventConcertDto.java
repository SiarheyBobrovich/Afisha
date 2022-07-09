package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dto.api.IEventConcertDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Validated
public class EventConcertDto extends EventDto implements IEventConcertDto {

    @NotNull(message = "Введите uuid категории")
    private final UUID category;

    public EventConcertDto(String title,
                           String description,
                           LocalDateTime dtEvent,
                           LocalDateTime dtEndOfSale,
                           Status status,
                           UUID category) {
        super(title, description, dtEvent, dtEndOfSale, status);
        this.category = category;
    }

    @Override
    public UUID getCategory() {
        return category;
    }
}
