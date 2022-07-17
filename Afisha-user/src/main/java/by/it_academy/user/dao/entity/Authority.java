package by.it_academy.user.dao.entity;

import by.it_academy.user.dao.enums.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * User's roles
 */

@Entity
@Table(schema = "security", name = "authorities")
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String authority;

    private Set<User> users;

    private Authority() {
    }

    private Authority(String authority) {
        this.authority = authority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToMany(mappedBy = "authorities")
    public Set<User> getUsers() {
        return users;
    }

    @Override
    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority role1 = (Authority) o;
        return Objects.equals(id, role1.id) && Objects.equals(authority, role1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }

    public static Authority of(Role role) {
        return new Authority(role.name());
    }

}
