package by.it_academy.afisha.dao.entity.events;

import by.it_academy.afisha.dao.entity.actions.Film;

import javax.persistence.*;

/**
 * Concrete event class
 */
@Entity
@Table(schema = "afisha", name = "events")
public class EventFilm extends Event {

    @OneToOne(targetEntity = Film.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "events_actions",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "action_uuid", referencedColumnName = "uuid")
    )
    private Film film;

    public Film getAction() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
