package com.luv2code.bookmyshow.movie;

import com.luv2code.bookmyshow.city.City;
import com.luv2code.bookmyshow.show.ShowResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = movieRepository.save(movieRequestDTO.toEntity());
        return MovieResponseDTO.fromEntity(movie);
    }

    public Set<ShowResponseDTO> getMovieShow(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        return movie.getShows().parallelStream().map(ShowResponseDTO::fromEntity).collect(Collectors.toSet());
    }

    public Page<MovieResponseDTO> getMoviesByCityId(City city, Integer pageNo, Integer pageSize) {
        Page<Movie> moviePage = movieRepository.findByShows_Auditorium_Theater_CityAndShows_ShowTimeGreaterThan(
                city, LocalDateTime.now(), PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "name")));
        return moviePage.map(MovieResponseDTO::fromEntity);
    }

    public Movie getMovieById(Integer movieId) {
        return movieRepository.findById(movieId).orElseThrow(() ->
                new EntityNotFoundException("movie with id [" + movieId + "] does not exist"));
    }
}
