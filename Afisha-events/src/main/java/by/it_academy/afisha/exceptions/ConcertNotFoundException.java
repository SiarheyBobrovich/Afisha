package by.it_academy.afisha.exceptions;

import javax.persistence.EntityNotFoundException;

/**
 * Thrown to indicate that a method has been passed an illegal classifier argument
 */
public class ConcertNotFoundException extends EntityNotFoundException {

    public ConcertNotFoundException() {
        super("Концерта не обнаружено: Проверьте uuid.");
    }
}
