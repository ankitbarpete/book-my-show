package com.luv2code.bookmyshow.auditorium;

import com.luv2code.bookmyshow.feature.Feature;
import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.seat.Seat;
import com.luv2code.bookmyshow.show.Show;
import com.luv2code.bookmyshow.theater.Theater;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Auditorium extends GenericEntity {
    private String name;
    private int maxRow;
    private int maxColumn;

    @ElementCollection
    @Enumerated
    private Set<Feature> supportedFeatures;

    @OneToMany(mappedBy = "auditorium", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Seat> seats = new LinkedHashSet<>();

    @OneToMany(mappedBy = "auditorium", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Show> shows = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Override
    public final boolean equals(Object o) {
        return this == o || (o instanceof Auditorium && Objects.equals(this.getId(), ((Auditorium) o).getId()));
    }

    @Override
    public final int hashCode() {
        return getId();
    }

}
