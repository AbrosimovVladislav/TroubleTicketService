package ru.vakoom.troubleticketservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vakoom.troubleticketservice.model.ScrapperOffer;
import ru.vakoom.troubleticketservice.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findByScrapperOffer(ScrapperOffer scrapperOffer);

}
