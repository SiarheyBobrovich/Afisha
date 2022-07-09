package by.it_academy.afisha.dto.api;

import java.time.LocalDate;
import java.util.UUID;

public interface IEventFilmDto {

    UUID getCountry();

    Integer getReleaseYear();

    LocalDate getReleaseDate();

    Integer getDuration();
}
