package com.luv2code.bookmyshow.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.id = :ticketId AND t.user.id = :userId AND t.status = :status")
    Optional<Ticket> findByIdAndUserIdAndStatus(@Param("ticketId") Integer ticketId, @Param("userId") Integer userId,
                                                @Param("status") Ticket.TicketStatus status);

}