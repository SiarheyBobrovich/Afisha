package by.it_academy.user.services;

import by.it_academy.user.dao.api.IUserDao;
import by.it_academy.user.dao.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {

    private final IUserDao userDao;

    public DetailsService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userDao.findByUserName(username);

        if (currentUser == null) {
            throw new UsernameNotFoundException("Не верный логин или пороль");
        }

        return currentUser;
    }
}
