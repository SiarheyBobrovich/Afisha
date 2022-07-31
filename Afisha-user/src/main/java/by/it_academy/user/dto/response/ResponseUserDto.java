package by.it_academy.user.dto.response;

import by.it_academy.user.dao.enums.Roles;
import by.it_academy.user.dao.enums.Status;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResponseUserDto {
    private final UUID uuid;
    private final String nick;
    private final String mail;

    private final String role;
    private final Status status;

    @Schema(implementation = Long.class)
    private final LocalDateTime dtCreate;
    @Schema(implementation = Long.class)
    private final LocalDateTime dtUpdate;

    public ResponseUserDto(UUID uuid,
                           String nick,
                           String mail,
                           String role,
                           Status status,
                           LocalDateTime dtCreate,
                           LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.nick = nick;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }
    //Getters

    public UUID getUuid() {
        return uuid;
    }

    public String getNick() {
        return nick;
    }

    public String getMail() {
        return mail;
    }

    public String getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public static Builder create() {
        return new Builder();
    }
    public static class Builder {
        private UUID uuid;
        private String nick;
        private String mail;
        private String role;
        private Status status;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        //Setters

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setNick(String nick) {
            this.nick = nick;
            return this;
        }

        public Builder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
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

        public ResponseUserDto build() {
            return new ResponseUserDto(
                    this.uuid,
                    this.nick,
                    this.mail,
                    this.role,
                    this.status,
                    this.dtCreate,
                    this.dtUpdate
            );
        }
    }
}
