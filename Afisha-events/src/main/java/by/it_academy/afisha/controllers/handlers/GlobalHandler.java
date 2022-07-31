package by.it_academy.afisha.controllers.handlers;

import by.it_academy.afisha.exceptions.*;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalHandler {

    private static final String LOGREF = "logref";
    private static final String MESSAGE = "message";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(RuntimeException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handle(JwtException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(IllegalStateException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Map<String, Object> handle(TypeNotImplementedException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(OptimisticLockException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(HttpMessageNotReadableException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, "Запрос содержит некорретные данные. Измените запрос и отправьте его ещё раз"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handle(EntityNotFoundException exception) {
        return Map.of(
                LOGREF, "error",
                MESSAGE, exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ConstraintViolationException exception) {
        final Map<String, Object> map = new HashMap<>();
        map.put(LOGREF, "structured_error");

        final List<Map<String, String>> errors = new ArrayList<>();

        exception.getConstraintViolations().forEach(x ->  {

            final String path = x.getPropertyPath().toString();
            final int pointIndex = x.getPropertyPath().toString().lastIndexOf(".");

            errors.add(Map.of(
                    "field", path.substring(pointIndex + 1),
                    MESSAGE, x.getMessage()
            ));
        });

        map.put("errors", errors);

        return map;
    }
}
