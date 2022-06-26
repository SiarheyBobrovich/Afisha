package by.it_academy.afisha.dao.api;

import by.it_academy.afisha.dao.entity.AbstractEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAbstractDao extends JpaRepository<AbstractEvent, UUID> {
}
