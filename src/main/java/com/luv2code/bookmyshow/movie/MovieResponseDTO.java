package com.luv2code.bookmyshow.movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.luv2code.bookmyshow.movie.Movie}
 */
public record MovieResponseDTO(Integer id, String name, List<String> casts,
                               LocalDate releaseDate) implements Serializable {
    public static MovieResponseDTO fromEntity(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getName(),
                movie.getCasts(),
                movie.getReleaseDate()
        );
    }
}