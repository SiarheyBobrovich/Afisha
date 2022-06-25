package by.it_academy.afisha.dao.entity.api;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

public abstract class AbstractEntity implements IBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;
    @Column(name = "title", nullable = false)
    private  String title;

    @Column(name = "description")
    private String description;

    protected AbstractEntity() {
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
