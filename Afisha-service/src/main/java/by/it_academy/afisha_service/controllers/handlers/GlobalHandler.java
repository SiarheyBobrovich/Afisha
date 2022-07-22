package by.it_academy.afisha_service.controllers.handlers;

import by.it_academy.afisha_service.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    private final String logref = "logref";
    private final String message = "message";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(RuntimeException exception) {
        return Map.of(
                logref, "error",
                message, "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(HttpMessageNotReadableException exception) {
        return Map.of(
                logref, "error",
                message, "Запрос не корректен, проверьте запрос"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handle(EntityNotFoundException exception) {
        return Map.of(
                logref, "error",
                message, "uuid не существует" //"Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ValidationException exception) {
        final List<Map.Entry<String, String>> exceptionErrors = exception.getErrors();

        final Map<String, Object> map = new HashMap<>();

        map.put(logref, "structured_error");

        final List<Map<String, String>> listErrors = new ArrayList<>();

        exceptionErrors.forEach(x -> listErrors.add(Map.of(
                "field", x.getKey(),
                message, x.getValue()
        )));

        map.put("errors", listErrors);

        return map;
    }
}