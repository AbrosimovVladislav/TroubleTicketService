package ru.vakoom.troubleticketservice.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vakoom.troubleticketservice.client.AggregatorClient;
import ru.vakoom.troubleticketservice.model.Product;
import ru.vakoom.troubleticketservice.service.ProductService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRefresher {

    private final AggregatorClient aggregatorClient;
    private final ProductService productService;

    @Scheduled(cron = "0 0 */12 * * *") // every 12 hours
    public ResponseEntity<List<Product>> refreshProducts() {
        List<Product> products = aggregatorClient.getProductsFromAggregator();
        return ResponseEntity.ok(productService.save(products));
    }

}
