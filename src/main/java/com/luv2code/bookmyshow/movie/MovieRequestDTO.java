package com.luv2code.bookmyshow.movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Movie}
 */
public record MovieRequestDTO(String name, List<String> casts, LocalDate releaseDate) implements Serializable {
    public Movie toEntity() {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setCasts(casts);
        movie.setReleaseDate(releaseDate);
        return movie;
    }
}