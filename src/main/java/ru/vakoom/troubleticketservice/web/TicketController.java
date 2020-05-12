package ru.vakoom.troubleticketservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vakoom.troubleticketservice.client.MatcherClient;
import ru.vakoom.troubleticketservice.model.MatcherOffer;
import ru.vakoom.troubleticketservice.model.Ticket;
import ru.vakoom.troubleticketservice.model.Type;
import ru.vakoom.troubleticketservice.service.TicketService;
import ru.vakoom.troubleticketservice.service.TypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final MatcherClient matcherClient;
    private final TypeService typeService;

    @PostMapping("/tickets")
    public Ticket createNewTicket(@RequestBody Ticket ticket) {
        ticket.getScrapperOffer().setId(null);
        return ticketService.saveNewTicket(ticket);
    }

    @CrossOrigin
    @GetMapping("/inProgress/{id}")
    public Ticket setTicketInProgress(@PathVariable Long id){
        return ticketService.setTicketInProgress(id);
    }

    @CrossOrigin
    @GetMapping("/tickets")
    public List<Ticket> getTickets() {
        return ticketService.findAll();
    }

    @CrossOrigin
    @GetMapping("/tickets/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @CrossOrigin
    @GetMapping("/resolveTicket")
    public Ticket resolve(@ModelAttribute MatcherOffer matcherOffer, @RequestParam Long ticketId) {
        Type type = typeService.findByShowName(matcherOffer.getType().getShowName());
        matcherOffer.setType(type);
        matcherClient.createMatcherOffer(matcherOffer);
        return ticketService.resolve(ticketId);
    }

}
