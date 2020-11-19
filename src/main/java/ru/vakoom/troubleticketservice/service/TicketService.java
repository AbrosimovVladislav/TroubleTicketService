package ru.vakoom.troubleticketservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vakoom.troubleticketservice.model.ScrapperOffer;
import ru.vakoom.troubleticketservice.model.Ticket;
import ru.vakoom.troubleticketservice.repo.ScrapperOfferRepository;
import ru.vakoom.troubleticketservice.repo.TicketRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ScrapperOfferRepository scrapperOfferRepository;

    public Ticket resolve(Long ticketId) {
        Ticket ticket = findById(ticketId);
        ticket.setStatus(Ticket.Status.RESOLVED);
        return ticketRepository.save(ticket);
    }

    public Ticket saveNewTicket(Ticket ticket) {
        log.info("Start working on ticket for offer: {}", ticket.getScrapperOffer().getLink());
        Optional<ScrapperOffer> scrapperOffer = scrapperOfferRepository.findByLink(ticket.getScrapperOffer().getLink());
        if (scrapperOffer.isEmpty()) {
            log.info("There is no already added ticket for offer: {}. Start process of ticket creation", ticket.getScrapperOffer().getLink());
            ticket.setStatus(Ticket.Status.NEW);
            return ticketRepository.save(ticket);
        } else {
            Ticket existingTicket = ticketRepository.findByScrapperOffer(scrapperOffer.get());
            log.info("Ticket already existed. Id {}, Status {}, Date {}, Offer {}",
                    existingTicket.getId(),
                    existingTicket.getStatus(),
                    existingTicket.getCreatedTime(),
                    existingTicket.getScrapperOffer().getLink());
            return existingTicket;
        }
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket setTicketInProgress(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) return new Ticket();
        ticket.get().setStatus(Ticket.Status.IN_PROGRESS);
        return ticketRepository.save(ticket.get());
    }
}
