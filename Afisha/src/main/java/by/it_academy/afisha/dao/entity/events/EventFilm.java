package by.it_academy.afisha.dao.entity.events;

import by.it_academy.afisha.dao.entity.actions.Film;

import javax.persistence.*;

@Entity
@Table(schema = "afisha", name = "events")
public class EventFilm extends Event {

    @OneToOne(targetEntity = Film.class, cascade = CascadeType.ALL)
    @JoinTable(name = "events_actions",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "action_uuid", referencedColumnName = "uuid")
    )
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}