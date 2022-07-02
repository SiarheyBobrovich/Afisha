package by.it_academy.afisha.dao.containers;

import by.it_academy.afisha.dao.entity.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DaoContainer {

    private final Map<Type, JpaRepository<?, ?>> classJpaRepositoryMap;

    public DaoContainer() {
        classJpaRepositoryMap = new HashMap<>();
    }
    
    public <T> void addJpaRepository(Type type, JpaRepository<T, UUID> repository) {
        classJpaRepositoryMap.put(type, repository);
    }

    public JpaRepository<?,?> getEventRepository(Type t) {
        return classJpaRepositoryMap.get(t);
    }
}
