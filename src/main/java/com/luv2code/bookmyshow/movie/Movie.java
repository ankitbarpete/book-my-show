package com.luv2code.bookmyshow.movie;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.show.Show;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie extends GenericEntity {
    private String name;

    @ElementCollection
    private List<String> casts = new ArrayList<>();
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Show> shows = new LinkedHashSet<>();

}