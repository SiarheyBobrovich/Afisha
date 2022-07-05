package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events", schema = "afisha", catalog = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID uuid;

    @Column(name = "status", nullable = false, length = 9)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "dt_create", updatable = false, nullable = false)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    private LocalDateTime dtUpdate;

    @OneToOne(targetEntity = Action.class, cascade = CascadeType.ALL)
    @JoinTable(name = "events_actions",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "action_uuid", referencedColumnName = "uuid")
    )
    private Action action;

    @Column(name = "dt_event", nullable = false)
    private LocalDateTime dtEvent;

    @Column(name = "dt_end_of_sale", nullable = false)
    private LocalDateTime dtEndOfSale;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private UUID uuid;
        private Status status;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private Action action;
        private LocalDateTime dtEvent;
        private LocalDateTime dtEndOfSale;

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
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

        public Builder setAction(Action action) {
            this.action = action;
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

        public Event build() {
            Event event = new Event();

            event.setUuid(this.uuid);
            event.setStatus(this.status);
            event.setDtCreate(this.dtCreate);
            event.setDtUpdate(this.dtUpdate);
            event.setAction(this.action);
            event.setDtEvent(this.dtEvent);
            event.setDtEndOfSale(this.dtEndOfSale);

            return event;
        }
    }
}
