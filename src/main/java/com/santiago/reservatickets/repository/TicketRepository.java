package com.santiago.reservatickets.repository;

import com.santiago.reservatickets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByIdAndStatus(Long id, String status);
}
