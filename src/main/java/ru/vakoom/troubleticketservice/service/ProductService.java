package ru.vakoom.troubleticketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vakoom.troubleticketservice.model.Product;
import ru.vakoom.troubleticketservice.repo.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> save(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<Product> products() {
        return productRepository.findAll();
    }
}
