package com.santiago.reservatickets.service;

import com.santiago.reservatickets.entity.Ticket;
import com.santiago.reservatickets.repository.TicketRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private static final String RESERVADO = "reservado";
    private static final String DISPONIBLE = "disponible";

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket reservarTicket(Long ticketId, String usuario) {
        try {
            Ticket ticket = ticketRepository.findByIdAndStatus(ticketId, DISPONIBLE)
                    .orElseThrow(() -> new RuntimeException("El ticket no está disponible"));

            ticket.setStatus(RESERVADO);
            ticket.setReservedBy(usuario);
            ticket.setReservedAt(LocalDateTime.now());

            return ticketRepository.save(ticket);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Eror de concurrencia.");
        }
    }
}
