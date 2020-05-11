package ru.vakoom.troubleticketservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vakoom.troubleticketservice.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByShowName(String showName);
}
