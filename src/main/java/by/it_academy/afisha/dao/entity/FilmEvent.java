package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "afisha")
public class FilmEvent extends AbstractEvent {

    @OneToOne(targetEntity = Film.class, cascade = CascadeType.ALL)
    @JoinTable(name = "events_films",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "film_uuid", referencedColumnName = "uuid")
    )

    private Film film;

    public Action getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private UUID uuid;
        private Film film;
        private Status status;
        private String currency;


        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        private LocalDateTime dtEndOfState;
        private LocalDateTime dtEvent;

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setFilm(Film film) {
            this.film = film;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public Builder setDtEndOfState(LocalDateTime dtEndOfState) {
            this.dtEndOfState = dtEndOfState;
            return this;
        }

        public Builder setDtEvent(LocalDateTime dtEvent) {
            this.dtEvent = dtEvent;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public FilmEvent build() {
            FilmEvent event = new FilmEvent();

            event.setUuid(this.uuid);
            event.setFilm(this.film);
            event.setStatus(this.status);
            event.setDtEvent(this.dtEvent);
            event.setDtEndOfState(this.dtEndOfState);
            event.setDtCreate(this.dtCreate);
            event.setDtUpdate(this.dtUpdate);
            event.setCurrency(currency);

            return event;
        }
    }
}
