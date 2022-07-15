package by.it_academy.afisha_user.dao.api;

import by.it_academy.afisha_user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserDao extends JpaRepository<User, UUID> {
}
