package com.luv2code.bookmyshow.ticket.cancelation;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.ticket.Ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the entity for the canceled ticket.
 */
@Getter
@Setter
@Entity
public class CanceledTicket extends GenericEntity {
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    private String reason;
    private Integer cancellationFeeInPercentage;
}
