package com.luv2code.bookmyshow.theater;

import com.luv2code.bookmyshow.auditorium.Auditorium;
import com.luv2code.bookmyshow.city.City;
import com.luv2code.bookmyshow.generic.GenericEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Theater extends GenericEntity {
    private String name;
    private String address;


    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "theater", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Auditorium> auditoriums = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        return o instanceof Theater && Objects.equals(this.getId(), ((Theater) o).getId());
    }

    @Override
    public final int hashCode() {
        return getId();
    }

}
