package by.it_academy.afisha.dao.entity.actions;

import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Concrete action class
 */
@Entity
@Table(schema = "afisha", name = "actions")
@SecondaryTable(schema = "afisha", name = "concerts_descriptions", pkJoinColumns = @PrimaryKeyJoinColumn(referencedColumnName = "uuid"))
public class Concert extends Action {

    private static final long serialVersionUID = 1L;

    private UUID category;

    public Concert() {
    }

    public Concert(UUID category) {
        this.category = category;
    }

    {
        super.setType(Type.CONCERTS);
    }

    @Column(table = "concerts_descriptions", name = "category", nullable = false)
    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concert)) return false;
        if (!super.equals(o)) return false;
        Concert concert = (Concert) o;
        return Objects.equals(category, concert.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category);
    }
}