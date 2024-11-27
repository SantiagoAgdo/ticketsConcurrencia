package com.santiago.reservatickets.scheduler;

import com.santiago.reservatickets.repository.TicketRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TicketCleanupScheduler {

    private final TicketRepository ticketRepository;

    private static final String RESERVADO = "reservado";
    private static final String DISPONIBLE = "disponible";

    public TicketCleanupScheduler(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void liberarTicketsReservados() {
        LocalDateTime limite = LocalDateTime.now().minusMinutes(15);
        ticketRepository.findAll().stream()
                .filter(ticket -> RESERVADO.equals(ticket.getStatus()) && ticket.getReservedAt().isBefore(limite))
                .forEach(ticket -> {
                    ticket.setStatus(DISPONIBLE);
                    ticket.setReservedBy(null);
                    ticket.setReservedAt(null);
                    ticketRepository.save(ticket);
                });
    }
}
