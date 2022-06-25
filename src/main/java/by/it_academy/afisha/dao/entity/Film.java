package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.api.AbstractEntity;
import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "films", schema = "afisha")
public class Film extends AbstractEntity {

    public Film() {
    }

    @Override
    public Type getType() {
        return Type.FILMS;
    }

}
