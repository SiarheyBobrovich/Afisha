package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.api.AbstractEntity;
import by.it_academy.afisha.dao.entity.api.IBaseEntity;
import by.it_academy.afisha.dao.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "afisha")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;
    @OneToOne(cascade = CascadeType.PERSIST)
    private IBaseEntity action;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(table = "base_event", name = "dt_create")
    private LocalDateTime dtCreate;
    @Version
    @Column(table = "base_event", name = "dt_update")
    private LocalDateTime dtUpdate;

    @Column(name = "dt_event", nullable = false)
    private LocalDateTime dtEvent;
    @Column(name = "dt_end_of_state")
    private LocalDateTime dtEndOfState;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public IBaseEntity getAction() {
        return action;
    }

    public void setAction(IBaseEntity action) {
        this.action = action;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        private String uuid;
        private IBaseEntity action;
        private Status status;

        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        private LocalDateTime dtEndOfState;
        private LocalDateTime dtEvent;

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setAction(IBaseEntity action) {
            this.action = action;
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

        public Event build() {
            Event event = new Event();

            event.setUuid(this.uuid);
            event.setAction(this.action);
            event.setStatus(this.status);
            event.setDtEvent(this.dtEvent);
            event.setDtEndOfState(this.dtEndOfState);
            event.setDtCreate(this.dtCreate);
            event.setDtUpdate(this.dtUpdate);

            return event;
        }
    }
}
