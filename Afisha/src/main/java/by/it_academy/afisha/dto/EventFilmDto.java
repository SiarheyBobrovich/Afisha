package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dto.api.IEventFilmDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventFilmDto extends EventDto implements IEventFilmDto {

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
                        int releaseYear,
                        LocalDate releaseDate,
                        int duration) {
        super(title, description, dtEvent, dtEndOfSale, status);
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    @Override
    public UUID getCountry() {
        return country;
    }

    @Override
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }

}
