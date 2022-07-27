package by.it_academy.afisha.exceptions;

/**
 * Thrown to indicate that Type has been updated but is not supported
 */
public class TypeNotImplementedException extends IllegalArgumentException {

    public TypeNotImplementedException() {
        super("Тип данных не обслуживается");
    }

}
