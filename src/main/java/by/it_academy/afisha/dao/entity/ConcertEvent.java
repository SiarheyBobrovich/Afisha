package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "afisha")
public class ConcertEvent extends AbstractEvent{

    @OneToOne(targetEntity = Concert.class, cascade = CascadeType.ALL)
    @JoinTable(name = "events_concerts",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "concert_uuid", referencedColumnName = "uuid")
    )
    private Concert concert;

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private UUID uuid;
        private Concert concert;
        private Status status;

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


        public ConcertEvent build() {
            ConcertEvent concertEvent = new ConcertEvent();

            concertEvent.setUuid(this.uuid);
            concertEvent.setConcert(this.concert);
            concertEvent.setStatus(this.status);
            concertEvent.setDtEvent(this.dtEvent);
            concertEvent.setDtEndOfState(this.dtEndOfState);
            concertEvent.setDtCreate(this.dtCreate);
            concertEvent.setDtUpdate(this.dtUpdate);

            return concertEvent;
        }
    }
}
