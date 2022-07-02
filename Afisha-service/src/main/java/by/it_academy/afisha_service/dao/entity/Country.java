package by.it_academy.afisha_service.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "countries", schema = "classifiers")
public class Country extends AbstractClassifier{

    private static final long serialVersionUID = 1L;
    @Column(name = "description", nullable = false)
    private String description;


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
