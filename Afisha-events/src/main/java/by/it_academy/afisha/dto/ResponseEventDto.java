package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;

import java.time.LocalDateTime;

public abstract class ResponseEventDto {


    private final String title;
    private final String description;

    private final LocalDateTime dtEvent;

    private final LocalDateTime dtEndOfSale;

    private final Status status;

    protected ResponseEventDto(String title,
                               String description,
                               LocalDateTime dtEvent,
                               LocalDateTime dtEndOfSale,
                               Status status) {
        this.title = title;
        this.description = description;
        this.dtEvent = dtEvent;
        this.dtEndOfSale = dtEndOfSale;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public Status getStatus() {
        return status;
    }

    public abstract Type getType();

}
