package by.it_academy.afisha.dao.entity.api;

import by.it_academy.afisha.dao.entity.enums.Type;

import javax.persistence.Entity;
import java.io.Serializable;

public interface IBaseEntity extends Serializable {

    String getUuid();
    Type getType();

    String getTitle();
    String getDescription();
}
