package ru.vakoom.troubleticketservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vakoom.troubleticketservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
