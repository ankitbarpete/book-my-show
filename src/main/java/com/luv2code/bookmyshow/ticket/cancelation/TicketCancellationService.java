package com.luv2code.bookmyshow.ticket.cancelation;

import com.luv2code.bookmyshow.showseat.ShowSeat;
import com.luv2code.bookmyshow.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TicketCancellationService {

    private final TicketCancellationRepository ticketCancellationRepository;

    public void cancelTicket(Ticket ticket, String cancellationReason) {
        CanceledTicket canceledTicket = new CanceledTicket();
        canceledTicket.setTicket(ticket);
        canceledTicket.setReason(cancellationReason);

        Integer cancellationFee = calculateCancellationFeeInPercentage(ticket);
        canceledTicket.setCancellationFeeInPercentage(cancellationFee);
        ticketCancellationRepository.save(canceledTicket);
    }

    private static Integer calculateCancellationFeeInPercentage(Ticket ticket) {
        int cancellationFee;
        LocalDateTime showTime = ticket.getShowSeats()
                .stream().findAny().orElse(new ShowSeat())
                .getShow().getShowTime();

        LocalDateTime now = LocalDateTime.now();
        if (showTime.isAfter(now.minusHours(24)))
            cancellationFee = 0;
        else if (showTime.isAfter(now.minusHours(12))) {
            cancellationFee = 10;
        } else if (showTime.isAfter(now.minusHours(6))){
            cancellationFee = 20;
        } else {
            cancellationFee = 50;
        }

        return cancellationFee;
    }
}
