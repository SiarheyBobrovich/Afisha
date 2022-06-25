package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFilmDao extends JpaRepository<Film, String> {

}
