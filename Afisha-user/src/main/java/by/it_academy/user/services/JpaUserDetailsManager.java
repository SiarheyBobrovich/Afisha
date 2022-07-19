package by.it_academy.user.services;

import by.it_academy.user.dao.api.IRoleDao;
import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.Role;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.services.api.IUserAuthenticateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.util.*;

@Service
public class JpaUserDetailsManager {
    private final IUserDao repository;
    private final IRoleDao roleDao;

    public JpaUserDetailsManager(IUserDao repository, IRoleDao roleDao) {
        this.repository = repository;
        this.roleDao = roleDao;
    }

    public void createUser(UserDetails user) {
        repository.save((User) user);
    }

    public void updateUser(UserDetails user) {
        User currentUser = repository.findByMail(user.getUsername());

        if (currentUser == null) {
            throw new EntityNotFoundException("Пользователь не найден");
        }

        if (!currentUser.getDtUpdate().equals(((User)user).getDtUpdate())) {
            throw new OptimisticLockException("Кто-то уже успел обновить пользователя");
        }

        User updatedUser = merge(currentUser, (User) user);

        Set<Role> roles = new HashSet<>();
        for (GrantedAuthority authority : user.getAuthorities()) {
            Role role = roleDao.findByAuthority(authority.getAuthority());

            if (Objects.isNull(role)) {
                throw new EntityNotFoundException("Не верно указана роль");
            }

            roles.add(roleDao.findByAuthority(authority.getAuthority()));
        }

        updatedUser.setAuthorities(roles);

        repository.save(updatedUser);
    }

    public void deleteUser(String username) {
            repository.deleteByUserName(username);
    }

    public void changePassword(String oldPassword, String newPassword) {
        User modifiedUser = repository.findFirstByPassword(oldPassword);

        modifiedUser.setPassword(newPassword);

        repository.save(modifiedUser);
    }

    public boolean userExists(String username) {
        return repository.findByUserName(username) != null;
    }

    public Page<User> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public User findByUuid(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    private User merge(User user, User source) {
        return User.builder()
                .setUuid(user.getUuid())
                .setDtUpdate(user.getDtUpdate())
                .setDtCreate(user.getDtCreate())
                .setNick(source.getNick())
                .setMail(source.getMail())
                .setStatus(source.getStatus())
                .setCredentialsNonExpired(source.isCredentialsNonExpired())
                .setAccountNonLocked(source.isAccountNonLocked())
                .setAccountNonExpired(source.isAccountNonExpired())
                .setEnabled(source.isEnabled())
                .setPassword(source.getPassword())

                .build();
    }
}
