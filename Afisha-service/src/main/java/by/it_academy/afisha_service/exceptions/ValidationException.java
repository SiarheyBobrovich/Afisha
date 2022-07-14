package by.it_academy.afisha_service.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Thrown to indicate params:value errors
 */
public class ValidationException extends IllegalArgumentException {
    private final List<Map.Entry<String, String>> errors;

    public ValidationException() {
        this.errors = new ArrayList<>();
    }

    public void add(Map.Entry<String, String> error) {
        errors.add(error);
    }

    public List<Map.Entry<String, String>> getErrors() {
        return errors;
    }
}
