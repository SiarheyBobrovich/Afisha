package by.it_academy.afisha_service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public class CategoryDto {
    private final String title;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryDto(@JsonSetter(value = "title", nulls = Nulls.FAIL) String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
