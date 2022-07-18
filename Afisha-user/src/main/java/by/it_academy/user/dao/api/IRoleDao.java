package by.it_academy.user.dao.api;

import by.it_academy.user.dao.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao extends JpaRepository<Authority, Long> {

    Authority findByAuthority(String authority);
}
