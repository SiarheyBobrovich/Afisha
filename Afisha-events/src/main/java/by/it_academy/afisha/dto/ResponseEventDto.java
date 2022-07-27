package by.it_academy.afisha.dto;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dao.entity.enums.Type;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseEventDto extends AbstractResponseEventDto {
    private final UUID uuid;
    private final LocalDateTime dtCreate;
    private final LocalDateTime dtUpdate;
    private final Type type;

    public ResponseEventDto(String title,
                            String description,
                            LocalDateTime dtEvent,
                            LocalDateTime dtEndOfSale,
                            Status status,
                            UUID uuid,
                            LocalDateTime dtCreate,
                            LocalDateTime dtUpdate,
                            Type type) {
        super(title, description, dtEvent, dtEndOfSale, status);
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public Type getType() {
        return type;
    }

}
