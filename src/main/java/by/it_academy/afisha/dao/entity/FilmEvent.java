package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.controllers.utils.LocalDateTimeSerializer;
import by.it_academy.afisha.dao.entity.enums.Status;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "afisha")

public class FilmEvent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @OneToOne(targetEntity = Film.class, cascade = CascadeType.ALL)
    @JoinTable(name = "events_films",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "film_uuid", referencedColumnName = "uuid")
    )
    private Film film;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "dt_create", updatable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;

    @Column(name = "dt_event", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtEvent;
    @Column(name = "dt_end_of_sale")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtEndOfState;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Action getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public LocalDateTime getDtEndOfState() {
        return dtEndOfState;
    }

    public void setDtEndOfState(LocalDateTime dtEndOfState) {
        this.dtEndOfState = dtEndOfState;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
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
