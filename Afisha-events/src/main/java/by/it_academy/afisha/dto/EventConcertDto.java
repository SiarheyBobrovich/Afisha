package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(builder = EventConcertDto.Builder.class)
public class EventConcertDto extends RequestEventDto {

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

    public UUID getCategory() {
        return category;
    }

    @Override
    public Type getType() {
        return Type.CONCERTS;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String title;
        private String description;
        private LocalDateTime dtEvent;
        private LocalDateTime dtEndOfSale;
        private Status status;
        private UUID category;

        //setters

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDtEvent(LocalDateTime dtEvent) {
            this.dtEvent = dtEvent;
            return this;
        }

        public Builder setDtEndOfSale(LocalDateTime dtEndOfSale) {
            this.dtEndOfSale = dtEndOfSale;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setCategory(UUID category) {
            this.category = category;
            return this;
        }

        public EventConcertDto build() {
            return new EventConcertDto(
                    this.title,
                    this.description,
                    this.dtEvent,
                    this.dtEndOfSale,
                    this.status,
                    this.category);
        }
    }
}
