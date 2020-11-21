package ru.vakoom.troubleticketservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vakoom.troubleticketservice.client.MatcherClient;
import ru.vakoom.troubleticketservice.model.MatcherOffer;
import ru.vakoom.troubleticketservice.model.Ticket;
import ru.vakoom.troubleticketservice.model.Type;
import ru.vakoom.troubleticketservice.service.TicketService;
import ru.vakoom.troubleticketservice.service.TypeService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TicketApiController implements TicketApi {

    private final TicketService ticketService;
    private final MatcherClient matcherClient;
    private final TypeService typeService;

    @PostMapping("/tickets")
    public ResponseEntity<List<Ticket>> saveTickets(@RequestBody List<Ticket> tickets) {
        log.info("Number of tickets from incoming request :{}", tickets.size());
        tickets.forEach(t -> t.getScrapperOffer().setId(null));
        return ResponseEntity.ok(ticketService.saveNewTickets(tickets));
    }

    @CrossOrigin
    @GetMapping("/inProgress/{id}")
    public Ticket setTicketInProgress(@PathVariable Long id) {
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
    public Ticket setTicketResolve(@ModelAttribute MatcherOffer matcherOffer, @RequestParam Long ticketId) {
        Type type = typeService.findByShowName(matcherOffer.getType().getShowName());
        matcherOffer.setType(type);
        matcherClient.createMatcherOffer(matcherOffer);
        return ticketService.resolve(ticketId);
    }

}
