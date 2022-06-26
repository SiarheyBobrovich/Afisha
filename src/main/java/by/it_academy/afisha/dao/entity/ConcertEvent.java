package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "afisha")
public class ConcertEvent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )    private UUID uuid;

    @OneToOne(targetEntity = Concert.class, cascade = CascadeType.ALL)
    @JoinTable(name = "events_concerts",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "concert_uuid", referencedColumnName = "uuid")
    )
    private Concert concert;

    @Column(name = "status", nullable = false, length = 9)
    private Status status;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "dt_create", updatable = false)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;

    @Column(name = "dt_event", nullable = false)
    private LocalDateTime dtEvent;
    @Column(name = "dt_end_of_sale", nullable = false)
    private LocalDateTime dtEndOfState;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
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
        private Concert concert;
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

        public Builder setConcert(Concert concert) {
            this.concert = concert;
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

        public ConcertEvent build() {
            ConcertEvent concertEvent = new ConcertEvent();

            concertEvent.setUuid(this.uuid);
            concertEvent.setConcert(this.concert);
            concertEvent.setStatus(this.status);
            concertEvent.setDtEvent(this.dtEvent);
            concertEvent.setDtEndOfState(this.dtEndOfState);
            concertEvent.setDtCreate(this.dtCreate);
            concertEvent.setDtUpdate(this.dtUpdate);
            concertEvent.setCurrency(currency);

            return concertEvent;
        }
    }
}
