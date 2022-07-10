package by.it_academy.afisha.dao.entity.events;

import by.it_academy.afisha.dao.entity.actions.Action;
import by.it_academy.afisha.dao.entity.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class Event implements Serializable {

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

    public abstract Action getAction();

}
