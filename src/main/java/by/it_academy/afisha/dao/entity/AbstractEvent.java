package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.controllers.utils.LocalDateTimeSerializer;
import by.it_academy.afisha.dao.entity.enums.Status;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractEvent {
    @Id
    private UUID uuid;

    @Column(name = "status", nullable = false, length = 9)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "dt_create", updatable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtCreate;
    @Version
    @Column(name = "dt_update", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtUpdate;

    @Column(name = "dt_event", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtEvent;
    @Column(name = "dt_end_of_sale", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dtEndOfState;

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
}
