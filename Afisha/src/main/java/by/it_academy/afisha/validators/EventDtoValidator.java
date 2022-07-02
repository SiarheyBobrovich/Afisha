package by.it_academy.afisha.validators;

import by.it_academy.afisha.dao.entity.enums.Status;
import by.it_academy.afisha.dto.EventDto;
import by.it_academy.afisha.exceptions.ValidationException;
import by.it_academy.afisha.validators.api.IValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EventDtoValidator implements IValidator<EventDto> {

    @Override
    public boolean validate(EventDto eventDto) {
        final Map<String, String> messages = new HashMap<>();

        final String pattern = "[\\p{Punct}\\p{Blank}\\p{Digit}\\p{Alpha}]";

        final String title = eventDto.getTitle();

        if (Objects.isNull(title) || title.isEmpty() || title.matches(pattern)) {
            messages.put("title", "Не верно введён титульный лист");
        }

        final String description = eventDto.getDescription();

        if (Objects.isNull(description) || description.matches(pattern)) {
            messages.put("description", "Не верно введено описание");
        }

        final Status status = eventDto.getStatus();

        if (Objects.isNull(status)) {
            messages.put("status", "Не верно введён статус");

        }else if (!status.equals(Status.DRAFT) && !status.equals(Status.PUBLISHED)) {
            messages.put("status", "Данный статус не обслуживается");

        }

        if (!messages.isEmpty()) {
            throw new ValidationException(messages);
        }

        return true;
    }
}
