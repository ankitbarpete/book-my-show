package com.luv2code.bookmyshow.ticket;

import com.luv2code.bookmyshow.exception.CancellationNotAllowedException;
import com.luv2code.bookmyshow.exception.TicketPriceCalculationException;
import com.luv2code.bookmyshow.showseat.ShowSeatRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("tickets")
public class TicketController {
    private final TicketService ticketService;

    /**
     * This method is used to book a ticket.
     * @param showSeats, takes ShowSeatRequestDTO object as input
     * @return ResponseEntity<TicketResponseDTO>
     */

    @PostMapping("book")
    public ResponseEntity<TicketResponseDTO> bookTicket(@RequestBody ShowSeatRequestDTO showSeats,
                                                        @RequestHeader("userId") Integer userId) {
        try {
            Ticket ticket = ticketService.bookTicket(showSeats, userId);
            return ResponseEntity
                    .created(ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}").buildAndExpand(ticket.getId()).toUri())
                    .body(TicketResponseDTO.fromEntity(ticket));
        } catch (TicketPriceCalculationException | EntityNotFoundException e) {
            log.error("error occurred", e);
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        } catch (IllegalArgumentException e) {
           log.error("error occurred", e);
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    /**
     * This method cancels ticket, takes a ticket id as argument and return canceled ticket
     */
    @PutMapping("{id}/cancel")
    public ResponseEntity<TicketResponseDTO> cancelTicket(@PathVariable("id") Integer ticketId,
                                                          @RequestParam("cancellationReason") String cancellationReason,
                                                          @RequestHeader("userId") Integer userId) {
        try {
            Ticket ticket = ticketService.cancelTicket(ticketId,  cancellationReason, userId);
            return ResponseEntity.ok(TicketResponseDTO.fromEntity(ticket));
        } catch (EntityNotFoundException e) {
            log.error("error occurred", e);
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        } catch (CancellationNotAllowedException e) {
            log.error("error occurred", e);
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }
}
