package by.it_academy.afisha.controllers.handlers;

import by.it_academy.afisha.exceptions.ValidationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(Throwable exception) {
        return Map.of(
                "logref", "error",
                "message", exception.getMessage()
//                "message", "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(HttpMessageNotReadableException exception) {
        return Map.of(
                "logref", "error",
                "message", exception.getCause().getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        final Map<String, Object> map = new HashMap<>();

        map.put("logref", "structured_error");

        final Map<String, String> errors = new HashMap<>(constraintViolations.size());

        constraintViolations.forEach(x ->  {
            final String[] split = x.getPropertyPath().toString().split("\\.");

            errors.put(split[split.length - 1], x.getMessage());
        });

        final List<Map<String, String>> listErrors = new ArrayList<>();

        for (Map.Entry<String, String> stringStringEntry : errors.entrySet()) {
            listErrors.add(Map.of(
                    "logref", stringStringEntry.getKey(),
                    "message", stringStringEntry.getValue())
            );
        }

        map.put("errors", listErrors);


        return map;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ValidationException exception) {

        final Map<String, Object> map = new HashMap<>();

        map.put("logref", "structured_error");

        final Map<String, String> errors = exception.getErrors();

        final List<Map<String, String>> listErrors = new ArrayList<>();

        for (Map.Entry<String, String> stringStringEntry : errors.entrySet()) {
            listErrors.add(Map.of(
                    "logref", stringStringEntry.getKey(),
                    "message", stringStringEntry.getValue())
            );
        }

        map.put("errors", listErrors);


        return map;
    }

}
