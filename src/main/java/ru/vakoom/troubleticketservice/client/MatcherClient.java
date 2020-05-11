package ru.vakoom.troubleticketservice.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.vakoom.troubleticketservice.model.MatcherOffer;

@Component
public class MatcherClient {

    public static final String MATCHER_BASE_PATH = "http://localhost:8081";
    public static final String MATCHER_CREATE_MATCHER_OFFER_PATH = "/matcher";

    private final RestTemplate restTemplate = new RestTemplate();

    public MatcherOffer createMatcherOffer(MatcherOffer matcherOffer) {
        String url = MATCHER_BASE_PATH + MATCHER_CREATE_MATCHER_OFFER_PATH;
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(matcherOffer),
                new ParameterizedTypeReference<MatcherOffer>() {
                }
        ).getBody();
    }

}
