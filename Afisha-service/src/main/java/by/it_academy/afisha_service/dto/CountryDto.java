package by.it_academy.afisha_service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CountryDto {

    private final String title;

    private final String description;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CountryDto(@JsonSetter(value = "title") String title,
                      @JsonSetter(value = "description") String description) {

        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
