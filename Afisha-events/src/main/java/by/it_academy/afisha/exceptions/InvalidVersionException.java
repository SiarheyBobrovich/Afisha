package by.it_academy.afisha.exceptions;

import javax.persistence.OptimisticLockException;

/**
 * Thrown to indicate that an object's version is out of date.
 */
public class InvalidVersionException extends OptimisticLockException {
    public InvalidVersionException() {
        super("Данные устарели: обновите данные");
    }
}
