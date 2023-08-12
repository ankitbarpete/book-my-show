package com.luv2code.bookmyshow.ticket;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.showseat.ShowSeat;
import com.luv2code.bookmyshow.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Ticket extends GenericEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Integer amount;

    @OneToMany(mappedBy = "ticket", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<ShowSeat> showSeats = new LinkedHashSet<>();

    private TicketStatus status;

    public enum TicketStatus {
        BOOKED,
        FAILED,
        CANCELLED
    }
}
