package by.it_academy.user.dto.request;

import by.it_academy.user.dao.enums.Roles;
import by.it_academy.user.dao.enums.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class UserCreateDto {
    private static final String NULL_MESSAGE = "Не должно быть пустым";

    @NotNull(message = NULL_MESSAGE)
    @Pattern(regexp = "[\\p{L}\\d]{5,20}",
            message = "Не верно введён nick")
    private final String nick;

    @NotNull(message = NULL_MESSAGE)
    @Email(regexp = "^[\\p{Alpha}\\d]+?@[\\p{Alpha}]+?\\.[\\p{Alpha}]+", message = "Не верно введён mail")
    private final String mail;
    @Pattern(regexp = "[\\p{Alpha}\\d]++",
            message = "Не верно введён password")
    private final String password;
    @NotNull(message = "Не верно введена role")
    private final Roles role;
    @NotNull(message = "Не верно введён status")
    private final Status status;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserCreateDto(@JsonProperty("nick") String nick,
                         @JsonProperty("mail") String mail,
                         @JsonProperty("password") String password,
                         @JsonProperty("role") Roles role,
                         @JsonProperty("status") Status status) {
        this.nick = nick;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getNick() {
        return nick;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDto entity = (UserCreateDto) o;
        return Objects.equals(this.nick, entity.nick) &&
                Objects.equals(this.mail, entity.mail) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.role, entity.role) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick, mail, password, role, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nick = " + nick + ", " +
                "mail = " + mail + ", " +
                "password = " + password + ", " +
                "role = " + role + ", " +
                "status = " + status + ")";
    }
}
