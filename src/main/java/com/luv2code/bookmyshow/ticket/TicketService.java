package com.luv2code.bookmyshow.ticket;

import com.luv2code.bookmyshow.exception.CancellationNotAllowedException;
import com.luv2code.bookmyshow.exception.TicketPriceCalculationException;
import com.luv2code.bookmyshow.showseat.ShowSeat;
import com.luv2code.bookmyshow.showseat.ShowSeatRequestDTO;
import com.luv2code.bookmyshow.showseat.ShowSeatService;
import com.luv2code.bookmyshow.ticket.cancelation.TicketCancellationService;
import com.luv2code.bookmyshow.user.User;
import com.luv2code.bookmyshow.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TicketService {

    private static final Integer CANCELLATION_ALLOWED_BEFORE_HOURS = 1;

    private final TicketRepository ticketRepository;
    private final ShowSeatService showSeatService;
    private final UserService userService;
    private final TicketCancellationService cancellationService;

    /**
     * This method is used to book a ticket.
     * @param showSeatRequestDTO, ShowSeatRequestDTO as input
     * @return ResponseEntity<TicketResponseDTO>
     */
    @Transactional
    public Ticket bookTicket(ShowSeatRequestDTO showSeatRequestDTO, Integer userId) throws TicketPriceCalculationException {
        // verify arguments
        if (userId == null || showSeatRequestDTO == null || showSeatRequestDTO.showId() == null
                || showSeatRequestDTO.seatIds() == null || showSeatRequestDTO.seatIds().isEmpty())
            throw new IllegalArgumentException("Invalid arguments");

        User user = userService.findUserById(userId);

        Set<ShowSeat> showSeats = showSeatService.getNotBookedShowSeatsByShowIdAndSeatIds(showSeatRequestDTO.showId(),
                showSeatRequestDTO.seatIds());
        if(showSeats.size() < showSeatRequestDTO.seatIds().size())
            throw new IllegalArgumentException("One or more seats are already booked, or invalid show/seat ids passed");

        Ticket ticket = new Ticket();
        int ticketAmount = showSeats.stream()
                .peek(showSeat -> showSeat.setTicket(ticket))
                .map(showSeat -> showSeat.getShow().getAdditionalPrice() + showSeat.getSeat().getSeatType().getPrice())
                .reduce(Integer::sum)
                .orElseThrow(() -> new TicketPriceCalculationException("Failed to calculate ticket price"));

        // some logic to do the transaction and based on status save the ticket to database, assuming status is success.
        // just booking the ticket
        ticket.setAmount(ticketAmount);
        ticket.setShowSeats(showSeats);
        ticket.setUser(user);
        ticket.setStatus(Ticket.TicketStatus.BOOKED);
        return ticketRepository.save(ticket);
    }

    /**
     * This method is used to cancel a ticket.
     *
     * @param ticketId,          id of the ticket to be cancelled
     * @param cancellationReason, cancellation reason
     * @param userId,             id of the user
     * @return ResponseEntity<TicketResponseDTO>
     */
    @Transactional
    public Ticket cancelTicket(Integer ticketId, String cancellationReason, Integer userId) throws CancellationNotAllowedException {
        Ticket ticket = ticketRepository.findByIdAndUserIdAndStatus(ticketId, userId, Ticket.TicketStatus.BOOKED)
                .orElseThrow(() -> new EntityNotFoundException("Ticket with Id [" + ticketId + "] does not exist"));

        Set<ShowSeat> showSeats = ticket.getShowSeats();
        LocalDateTime showTime = showSeats.stream().findAny().orElseThrow().getShow().getShowTime();
        if (showTime.isBefore(LocalDateTime.now().minusHours(CANCELLATION_ALLOWED_BEFORE_HOURS)))
            throw new CancellationNotAllowedException("Cancellation is allowed before One hour of show time");

        cancellationService.cancelTicket(ticket, cancellationReason);

        showSeats.forEach(showSeat -> showSeat.setTicket(null));
        ticket.setShowSeats(null);
        ticket.setStatus(Ticket.TicketStatus.CANCELLED);
        return ticketRepository.save(ticket);
    }
}
