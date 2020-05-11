package ru.vakoom.troubleticketservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vakoom.troubleticketservice.model.ScrapperOffer;

import java.util.Optional;

public interface ScrapperOfferRepository extends JpaRepository<ScrapperOffer, Long> {

    Optional<ScrapperOffer> findByLink(String link);

}
