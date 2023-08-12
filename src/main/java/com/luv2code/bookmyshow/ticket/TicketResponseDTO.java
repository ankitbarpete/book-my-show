package com.luv2code.bookmyshow.ticket;

import com.luv2code.bookmyshow.showseat.ShowSeat;
import com.luv2code.bookmyshow.showseat.ShowSeatResponseDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Ticket}
 */
public record TicketResponseDTO(Integer id, LocalDateTime createdTime, String userName, Integer amount,
                                ShowSeatResponseDTO showSeats) implements Serializable {
    public static TicketResponseDTO fromEntity(Ticket ticket) {
        Set<ShowSeat> showSeats = ticket.getShowSeats();
        ShowSeatResponseDTO showSeatResponseDTO = showSeats == null ? null :
                ShowSeatResponseDTO.fromEntityList(showSeats.stream().toList());
        return new TicketResponseDTO(ticket.getId(), ticket.getCreatedTime(), ticket.getUser().getName(),
                ticket.getAmount(),showSeatResponseDTO);
    }
}