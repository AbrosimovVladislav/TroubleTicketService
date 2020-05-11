package ru.vakoom.troubleticketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.troubleticketservice.model.ScrapperOffer;
import ru.vakoom.troubleticketservice.model.Ticket;
import ru.vakoom.troubleticketservice.repo.ScrapperOfferRepository;
import ru.vakoom.troubleticketservice.repo.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ScrapperOfferRepository scrapperOfferRepository;

    public Ticket resolve(Long ticketId) {
        Ticket ticket = findById(ticketId);
        ticket.setResolved(true);
        ticket.setInProgress(false);
        return ticketRepository.save(ticket);
    }

    public Ticket saveNewTicket(Ticket ticket) {
        Optional<ScrapperOffer> scrapperOffer = scrapperOfferRepository.findByLink(ticket.getScrapperOffer().getLink());
        if (scrapperOffer.isEmpty()) ticketRepository.save(ticket);
        return ticketRepository.findByScrapperOffer(scrapperOffer.get());
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
