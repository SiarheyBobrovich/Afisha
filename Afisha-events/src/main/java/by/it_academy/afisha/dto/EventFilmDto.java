package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(builder = EventFilmDto.Builder.class)
public class EventFilmDto extends EventDto {

    @NotNull(message = "Введите uuid страны")
    private final UUID country;

    @NotNull(message = "Введите год выпуска")
    @Min(1900)
    private final Integer releaseYear;

    @NotNull(message = "Введите дату выпуска")
    private final LocalDate releaseDate;

    @NotNull(message = "Введите продолжительность")
    private final Integer duration;

    public EventFilmDto(String title,
                        String description,
                        LocalDateTime dtEvent,
                        LocalDateTime dtEndOfSale,
                        Status status,
                        UUID country,
                        Integer releaseYear,
                        LocalDate releaseDate,
                        Integer duration) {
        super(title, description, dtEvent, dtEndOfSale, status);
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public UUID getCountry() {
        return country;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    @Override
    public Type getType() {
        return Type.FILMS;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private String title;
        private String description;
        private LocalDateTime dtEvent;
        private LocalDateTime dtEndOfSale;
        private Status status;
        private UUID country;
        private Integer releaseYear;
        @JsonFormat(pattern = "dd MMMM yyyy")
        private LocalDate releaseDate;
        private Integer duration;

        //Setters

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

        public Builder setCountry(UUID country) {
            this.country = country;
            return this;
        }

        public Builder setReleaseYear(Integer releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Builder setReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public EventFilmDto build() {
            return new EventFilmDto(
                    this.title,
                    this.description,
                    this.dtEvent,
                    this.dtEndOfSale,
                    this.status,
                    this.country,
                    this.releaseYear,
                    this.releaseDate,
                    this.duration);
        }
    }
}
