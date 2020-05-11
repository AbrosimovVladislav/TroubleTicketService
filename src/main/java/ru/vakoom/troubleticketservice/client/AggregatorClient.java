package ru.vakoom.troubleticketservice.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vakoom.troubleticketservice.model.Product;

import java.util.List;

@Component
public class AggregatorClient {

    public static final String AGGREGATOR_BASE_PATH = "http://localhost:8082";
    public static final String AGGREGATOR_PRODUCTS_PATH = "/allProducts";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Product> getProductsFromAggregator() {
        String url = AGGREGATOR_BASE_PATH + AGGREGATOR_PRODUCTS_PATH;
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                RequestEntity.EMPTY,
                new ParameterizedTypeReference<List<Product>>() {
                }
        ).getBody();
    }

}
