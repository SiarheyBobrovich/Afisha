package by.it_academy.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginDto {

    @NotNull
    @Email(regexp = "^[\\p{Alpha}\\d]+?@[\\p{Alpha}]+?\\.[\\p{Alpha}]+", message = "Не верно введён mail")
    private final String mail;

    @NotNull
    @Pattern(regexp = "[\\p{Alpha}\\d]++",
            message = "Не верно введён password")
    private final String password;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserLoginDto(@JsonProperty("mail") String mail,
                        @JsonProperty("password") String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
