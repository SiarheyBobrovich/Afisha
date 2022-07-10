package by.it_academy.afisha.dto.api;

import by.it_academy.afisha.dao.entity.enums.Status;

import java.time.LocalDateTime;

public interface IEventDto {

    String getTitle();

    String getDescription();

    LocalDateTime getDtEvent();

    LocalDateTime getDtEndOfSale();

    Status getStatus();
}
