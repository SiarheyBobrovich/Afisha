package by.it_academy.user.dao.api;

import by.it_academy.user.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);
}
