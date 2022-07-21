package by.it_academy.afisha.controllers.handlers;

import by.it_academy.afisha.exceptions.EntityNotFoundException;
import by.it_academy.afisha.exceptions.InvalidVersionException;
import by.it_academy.afisha.exceptions.TypeNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(RuntimeException exception) {
        return Map.of(
                "logref", "error",
                "message", "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(EntityNotFoundException exception) {
        return Map.of(
                "logref", "error",
                "message", exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Map<String, Object> handle(TypeNotSupportedException exception) {
        return Map.of(
                "logref", "error",
                "message", exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(InvalidVersionException exception) {
        return Map.of(
                "logref", "error",
                "message", exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Map<String, Object> handle(HttpMessageNotReadableException exception) {
        return Map.of(
                "logref", "error",
                "message", "Тип данных не обслуживается"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ConstraintViolationException exception) {
        final Map<String, Object> map = new HashMap<>();
        map.put("logref", "structured_error");

        final List<Map<String, Object>> errors = new ArrayList<>();

        exception.getConstraintViolations().forEach(x ->  {

            final String path = x.getPropertyPath().toString();
            final int pointIndex = x.getPropertyPath().toString().lastIndexOf(".");

            errors.add(Map.of(
                    "field", path.substring(pointIndex + 1),
                    "message", x.getMessage()
            ));
        });

        map.put("errors", errors);

        return map;
    }
}
