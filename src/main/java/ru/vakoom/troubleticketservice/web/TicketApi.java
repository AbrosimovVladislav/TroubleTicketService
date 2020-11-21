package ru.vakoom.troubleticketservice.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vakoom.troubleticketservice.model.MatcherOffer;
import ru.vakoom.troubleticketservice.model.Ticket;

import java.util.List;

public interface TicketApi {

    @ApiOperation(value = "Create new ticket",
            notes = "Create new ticket and save it to db",
            response = Ticket.class)
    ResponseEntity<List<Ticket>> saveTickets(@RequestBody List<Ticket> tickets);

    @ApiOperation(value = "Set ticket in progress",
            notes = "Get ticket by input id, and set it's status to in progress",
            response = Ticket.class)
    Ticket setTicketInProgress(@PathVariable Long id);

    @ApiOperation(value = "Get tickets",
            notes = "Get actual list of tickets",
            response = List.class)
    List<Ticket> getTickets();

    @ApiOperation(value = "Get ticket",
            notes = "Get one ticket by id",
            response = Ticket.class)
    Ticket getTicket(@PathVariable Long id);

    @ApiOperation(value = "Set ticket resolve",
            notes = "Get ticket by input id, and set it's status to resolved",
            response = Ticket.class)
    Ticket setTicketResolve(@ModelAttribute MatcherOffer matcherOffer, @RequestParam Long ticketId);

}
