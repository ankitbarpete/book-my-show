package com.luv2code.bookmyshow.showseat;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.seat.Seat;
import com.luv2code.bookmyshow.show.Show;
import com.luv2code.bookmyshow.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "ShowSeat", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seat_id", "show_id"})
})
public class ShowSeat extends GenericEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Override
    public final boolean equals(Object o) {
        return o instanceof ShowSeat && getId() != null && Objects.equals(getId(), ((ShowSeat)o).getId());
    }

    @Override
    public final int hashCode() {
        return this.getId();
    }

}
