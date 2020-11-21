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
import java.util.stream.Collectors;

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

    public List<Ticket> saveNewTickets(List<Ticket> tickets) {
        List<Ticket> savedTickets = tickets.stream()
                .filter(t -> scrapperOfferRepository.findByLink(t.getScrapperOffer().getLink()).isEmpty())
                .map(t -> ticketRepository.save(t.setStatus(Ticket.Status.NEW)))
                .collect(Collectors.toList());
        log.info("Number of saved tickets: {}",savedTickets.size());
        return savedTickets;
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
