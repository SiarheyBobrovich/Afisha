package by.it_academy.afisha.dao.entity.actions;

import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "afisha", name = "actions")
@SecondaryTable(schema = "afisha", name = "films_descriptions",
        pkJoinColumns = @PrimaryKeyJoinColumn(referencedColumnName = "uuid")
)
public class Film extends Action {

    private static final long serialVersionUID = 1L;

    private UUID country;
    private int releaseYear;
    private LocalDate releaseDate;
    private int duration;

    public Film() {
    }

    public Film(UUID country, int releaseYear, LocalDate releaseDate, int duration) {
        this.country = country;
        this.releaseYear = releaseYear;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    {
        super.setType(Type.FILMS);
    }

    @Column(table = "films_descriptions", name = "country", nullable = false)
    public UUID getCountry() {
        return country;
    }

    @Column(table = "films_descriptions", name = "release_year", nullable = false)
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @Column(table = "films_descriptions", name = "release_date", nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Column(table = "films_descriptions", name = "duration", nullable = false)
    public Integer getDuration() {
        return duration;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return Objects.equals(country, film.country) && Objects.equals(releaseYear, film.releaseYear) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(duration, film.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, releaseYear, releaseDate, duration);
    }
}
