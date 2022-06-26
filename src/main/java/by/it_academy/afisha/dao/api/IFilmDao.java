package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFilmDao extends JpaRepository<Action, UUID> {

}
