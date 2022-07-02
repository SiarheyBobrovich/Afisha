package by.it_academy.afisha.dao.entity;


import by.it_academy.afisha.dao.entity.enums.Type;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@JsonSerialize
@Entity(name = "actions")
@Table(name = "actions", schema = "afisha", catalog = "events")
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private UUID uuid;
    private String title;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
