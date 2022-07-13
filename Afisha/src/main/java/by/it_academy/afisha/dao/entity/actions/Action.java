package by.it_academy.afisha.dao.entity.actions;


import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract actions class
 */
@MappedSuperclass
public abstract class Action implements Serializable {

    private UUID uuid;
    private String title;
    private String description;

    private Type type;

    @Id
    @Column(nullable = false, updatable = false)
    public UUID getUuid() {
        return uuid;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;
        Action action = (Action) o;
        return Objects.equals(uuid, action.uuid) &&
                Objects.equals(title, action.title) &&
                Objects.equals(description, action.description) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, description);
    }
}
