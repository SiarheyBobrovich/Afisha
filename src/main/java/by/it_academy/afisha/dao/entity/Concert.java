package by.it_academy.afisha.dao.entity;

import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.*;

@Entity
@Table(name = "concerts", schema = "afisha")
public class Concert extends Action {

    @Override
    public Type getType() {
        return Type.CONCERTS;
    }

}

