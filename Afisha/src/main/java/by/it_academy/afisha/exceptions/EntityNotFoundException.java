package by.it_academy.afisha.exceptions;

import java.util.UUID;

/**
 * Thrown to indicate that a method has been passed an illegal classifier argument
 */
public class EntityNotFoundException extends IllegalArgumentException {

    private final UUID uuid;

    public EntityNotFoundException(UUID uuid, String s ) {
        super(s);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
