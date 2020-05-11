package ru.vakoom.troubleticketservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.troubleticketservice.model.Product;
import ru.vakoom.troubleticketservice.scheduler.ProductRefresher;
import ru.vakoom.troubleticketservice.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final ProductRefresher productRefresher;

    @GetMapping("/refreshProducts")
    public void refreshProducts() {
        productRefresher.refreshProducts();
    }

    @CrossOrigin
    @GetMapping("/products")
    public ResponseEntity<List<Product>> products() {
        return ResponseEntity.ok(productService.products());
    }
}
