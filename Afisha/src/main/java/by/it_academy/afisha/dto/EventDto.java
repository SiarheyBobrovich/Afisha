package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dto.api.IDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonDeserialize(builder = EventDto.Builder.class)
public class EventDto implements IDto {

    private final String title;
    private final String description;
    private final LocalDateTime dtEvent;
    private final LocalDateTime dtEndOfSale;
    private final Status status;

    public EventDto(String title, String description, LocalDateTime dtEvent,
                    LocalDateTime dtEndOfSale, Status status) {
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

    public static Builder create() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(title, eventDto.title) &&
                Objects.equals(description, eventDto.description) &&
                Objects.equals(dtEvent, eventDto.dtEvent) &&
                Objects.equals(dtEndOfSale, eventDto.dtEndOfSale) &&
                status == eventDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, dtEvent, dtEndOfSale, status);
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dtEvent=" + dtEvent +
                ", dtEndOfSale=" + dtEndOfSale +
                ", status=" + status +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String title;
        private String description;

        private LocalDateTime dtEvent;

        private LocalDateTime dtEndOfSale;
        private Status status;
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

        public EventDto build() {
            return new EventDto(title, description, dtEvent, dtEndOfSale, status);
        }
    }

}
