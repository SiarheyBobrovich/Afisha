package by.it_academy.afisha.exceptions;

import java.util.Map;

public class ValidationException extends IllegalArgumentException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
