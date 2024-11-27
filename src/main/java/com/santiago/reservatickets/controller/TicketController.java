package com.santiago.reservatickets.controller;

import com.santiago.reservatickets.entity.Ticket;
import com.santiago.reservatickets.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{ticketId}/reserve")
    public ResponseEntity<Object> reservarTicket(@PathVariable Long ticketId, @RequestParam String usuario) {
        try {
            Ticket ticket = ticketService.reservarTicket(ticketId, usuario);
            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
