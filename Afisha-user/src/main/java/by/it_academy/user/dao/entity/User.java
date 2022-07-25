package by.it_academy.user.dao.entity;

import by.it_academy.user.dao.enums.Status;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "security", name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    private UUID uuid;
    private String mail;
    private String password;
    private String nick;
    private Set<Role> authorities;
    private Status status;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    public User() {
    }

    @Id
    @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    public UUID getUuid() {
        return uuid;
    }

    @Override
    @Transient
    public String getUsername() {
        return getMail();
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getMail() {
        return mail;
    }

    @Override
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "nick", nullable = false)
    public String getNick() {
        return nick;
    }

    @Override
    @OneToMany(fetch = FetchType.EAGER)
    public Set<Role> getAuthorities() {
        return authorities;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public Status getStatus() {
        return status;
    }

    @Override
    @Column(name = "account_non_expired")
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    @Column(name = "account_non_locked")
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    @Column(name = "credentials_non_expired")
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    @Column(name = "enabled")
    public boolean isEnabled() {
        return this.enabled;
    }

    @Column(name = "dt_create", nullable = false, updatable = false)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @Version
    @Column(name = "dt_update", nullable = false)
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UUID uuid;
        private String mail;
        private String password;
        private String nick;
        private Set<Role> authorities;
        private Status status;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setNick(String nick) {
            this.nick = nick;
            return this;
        }

        public Builder setAuthorities(Set<Role> authorities) {
            this.authorities = authorities;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public Builder setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public User build() {
            User user = new User();

            user.setUuid(this.uuid);
            user.setMail(this.mail);
            user.setPassword(this.password);
            user.setNick(this.nick);
            user.setAuthorities(this.authorities);
            user.setStatus(this.status);
            user.setAccountNonExpired(this.accountNonExpired);
            user.setAccountNonLocked(this.accountNonLocked);
            user.setCredentialsNonExpired(this.credentialsNonExpired);
            user.setEnabled(this.enabled);
            user.setDtCreate(this.dtCreate);
            user.setDtUpdate(this.dtUpdate);

            return user;
        }
    }
}