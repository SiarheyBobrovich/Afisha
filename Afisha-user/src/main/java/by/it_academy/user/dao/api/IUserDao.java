package by.it_academy.user.dao.api;

import by.it_academy.user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserDao extends JpaRepository<User, UUID> {
    @Query
    User findByMail(String mail);

    @Query
    boolean existsByMail(String mail);

    default User findByUserName(String username) {
        return findByMail(username);
    }

}
