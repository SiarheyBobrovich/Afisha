package by.it_academy.afisha.dao.entity.events;

import by.it_academy.afisha.dao.entity.actions.Concert;

import javax.persistence.*;

/**
 * Concrete event class
 */
@Entity
@Table(schema = "afisha", name = "events")
public class EventConcert extends Event {

    @OneToOne(targetEntity = Concert.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "events_actions",
            joinColumns = @JoinColumn(name = "event_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "action_uuid", referencedColumnName = "uuid")
    )
    private Concert concert;

    public EventConcert() {
    }

    public EventConcert(Concert concert) {
        this.concert = concert;
    }

    @Override
    public Concert getAction() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
