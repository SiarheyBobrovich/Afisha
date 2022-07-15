package by.it_academy.afisha_user.dto.request;

import by.it_academy.afisha_user.dao.enums.Role;
import by.it_academy.afisha_user.dao.enums.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

public class UserCreateDto implements Serializable {

    @NotNull
    @Pattern(regexp = "[\\p{L}\\d\\p{Blank}]++",
            message = "Не верно введён nick")
    private final String nick;

    @NotNull
    @Email(message = "не верно введён mail")
    private final String mail;
    @Pattern(regexp = "[\\p{Alpha}\\d]++",
            message = "Не верно введён password")
    private final String password;
    @NotNull(message = "Не верно введена role")
    private final Role role;
    @NotNull(message = "Не верно введён status")
    private final Status status;

    public UserCreateDto(String nick, String mail, String password, Role role, Status status) {
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

    public Role getRole() {
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
