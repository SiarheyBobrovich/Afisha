package by.it_academy.user.services;

import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import by.it_academy.user.services.api.IUserAuthenticateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class JpaUserDetailsManager implements IUserAuthenticateService {
    private final IUserDao repository;

    public JpaUserDetailsManager(IUserDao repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(UserDetails user) {
        repository.save((User) user);
    }

    @Override
    public void updateUser(UserDetails user) {
        repository.save((User)user);
    }

    @Override
    public void deleteUser(String username) {
            repository.deleteByUserName(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        User modifiedUser = repository.findFirstByPassword(oldPassword);

        modifiedUser.setPassword(newPassword);

        repository.save(modifiedUser);
    }

    @Override
    public boolean userExists(String username) {
        return repository.findByUserName(username) != null;
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User currentUser = repository.findByUserName(username);

        if (currentUser == null) {
            throw new SecurityException("Не верный логин или пороль");
        }

        return currentUser;
    }

    @Override
    public User findByUuid(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }
}
