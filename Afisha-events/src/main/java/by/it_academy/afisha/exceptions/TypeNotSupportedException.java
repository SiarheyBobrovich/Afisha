package by.it_academy.afisha.exceptions;

import by.it_academy.afisha.dao.entity.enums.Type;

/**
 * Thrown to indicate that Type has been updated but is not supported
 */
public class TypeNotSupportedException extends IllegalArgumentException {

    private final Type type;

    public TypeNotSupportedException(Type type) {
        super("Тип данных не обслуживается");
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
