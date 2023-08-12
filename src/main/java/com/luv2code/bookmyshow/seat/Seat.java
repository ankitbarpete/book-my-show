package com.luv2code.bookmyshow.seat;

import com.luv2code.bookmyshow.auditorium.Auditorium;
import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.showseat.ShowSeat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"seat_row", "seat_col", "auditorium_id"}))
public class Seat extends GenericEntity {
    private String seatNum;
    @Column(name = "seat_row")
    private int seatRow;
    @Column(name="seat_col")
    private int seatCol;

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;

    @Enumerated
    private SeatStatus seatStatus;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;

    @OneToMany(mappedBy = "seat", orphanRemoval = true)
    private Set<ShowSeat> showSeat = new LinkedHashSet<>();

    @Transient
    private String showSeatStatus;

    @Override
    public int hashCode() {
        return super.getId();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Seat && Objects.equals(this.getId(), ((Seat) obj).getId());
    }

}
