package by.it_academy.afisha_service.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories", schema = "classifiers")
public class Category extends AbstractClassifier{

    private static final long serialVersionUID = 1L;

}
