package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.*;


@Entity
@Table(name = "films", schema = "afisha")
public class Film extends Action {

    @Override
    public Type getType() {
        return Type.FILMS;
    }

}
