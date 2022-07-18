package by.it_academy.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistrationDto {

    @NotNull
    @Email(regexp = "^[\\p{Alpha}\\d]+?@[\\p{Alpha}]+?\\.[\\p{Alpha}]+?", message = "Не верно введён mail")
    private final String mail;

    @NotNull
    @Pattern(regexp = "[\\p{L}\\d]++",
            message = "Не верно введён nick")
    private final String nick;

    @NotNull
    @Pattern(regexp = "[\\p{Alpha}\\d]++",
            message = "Не верно введён password")
    private final String password;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserRegistrationDto(@JsonProperty("mail") String mail,
                               @JsonProperty("nick") String nick,
                               @JsonProperty("password") String password) {
        this.mail = mail;
        this.nick = nick;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }
}
