package by.it_academy.afisha_service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseCategoryDto implements Serializable {
    private final UUID uuid;
    private final String title;
    private final LocalDateTime dtCreate;
    private final LocalDateTime dtUpdate;

    public ResponseCategoryDto(UUID uuid, String title, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.title = title;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
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
}
