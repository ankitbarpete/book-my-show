package com.luv2code.bookmyshow.ticket.cancelation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCancellationRepository extends JpaRepository<CanceledTicket, Integer> {
}