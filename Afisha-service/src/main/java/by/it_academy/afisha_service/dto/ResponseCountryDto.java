package by.it_academy.afisha_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseCountryDto {

    private final UUID uuid;
    private final String title;
    private final LocalDateTime dtCreate;
    private final LocalDateTime dtUpdate;
    private final String description;

    public ResponseCountryDto(UUID uuid, String title, LocalDateTime dtCreate, LocalDateTime dtUpdate, String description) {
        this.uuid = uuid;
        this.title = title;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getDescription() {
        return description;
    }
}
