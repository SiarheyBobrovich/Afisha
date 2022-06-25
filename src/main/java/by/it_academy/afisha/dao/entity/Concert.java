package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.api.AbstractEntity;
import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "concerts", schema = "afisha")
public class Concert extends AbstractEntity {

    public Concert() {
    }

    @Override
    public Type getType() {
        return Type.CONCERTS;
    }
}

