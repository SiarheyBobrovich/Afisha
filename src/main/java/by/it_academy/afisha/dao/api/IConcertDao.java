package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface IConcertDao extends JpaRepository<Concert, String> {


}
