package com.luv2code.bookmyshow.show;

import com.luv2code.bookmyshow.auditorium.Auditorium;
import com.luv2code.bookmyshow.feature.Feature;
import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.movie.Movie;
import com.luv2code.bookmyshow.showseat.ShowSeat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Show extends GenericEntity {
    private String showName;
    private LocalDateTime showTime;
    @ElementCollection
    private Set<Feature> requiredFeatures;
    private int additionalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;

    @OneToMany(mappedBy = "show", orphanRemoval = true)
    private Set<ShowSeat> showSeats = new LinkedHashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Show show = (Show) o;
        return Objects.equals(getId(), show.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
