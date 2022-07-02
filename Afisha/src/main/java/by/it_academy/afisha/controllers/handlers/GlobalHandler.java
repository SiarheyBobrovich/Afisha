package by.it_academy.afisha.controllers.handlers;

import by.it_academy.afisha.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
