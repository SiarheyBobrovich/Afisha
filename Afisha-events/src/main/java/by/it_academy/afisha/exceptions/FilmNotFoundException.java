package by.it_academy.afisha.exceptions;

import javax.persistence.EntityNotFoundException;

/**
 * Thrown to indicate that a method has been passed an illegal classifier argument
 */
public class FilmNotFoundException extends EntityNotFoundException {

    public FilmNotFoundException() {
        super("Фильма не обнаружено: Проверьте uuid.");
    }
}
