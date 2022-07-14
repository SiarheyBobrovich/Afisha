package by.it_academy.afisha.exceptions;

/**
 * Thrown to indicate that an object's version is out of date.
 */
public class InvalidVersionException extends IllegalArgumentException {
    public InvalidVersionException() {
        super("Обновите данные о событии");
    }
}
