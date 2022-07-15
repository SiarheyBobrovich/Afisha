package by.it_academy.afisha_user.dao.api;

import by.it_academy.afisha_user.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserDao extends JpaRepository<User, UUID> {
}
