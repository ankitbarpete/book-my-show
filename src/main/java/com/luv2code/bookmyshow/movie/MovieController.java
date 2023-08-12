package com.luv2code.bookmyshow.movie;

import com.luv2code.bookmyshow.show.ShowResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/movies"))
public class MovieController {
    private final MovieService movieService;

    @PostMapping()
    ResponseEntity<MovieResponseDTO> addMovie(@RequestBody MovieRequestDTO movie) {
        MovieResponseDTO savedMovie = movieService.addMovie(movie);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/movies/"+savedMovie.id())
                        .build()
                        .toUri())
                .body(savedMovie);
    }

    @GetMapping("/{movieId}/shows")
    ResponseEntity<Set<ShowResponseDTO>> getMovieShow(@PathVariable Integer movieId) {
        Set<ShowResponseDTO> shows = movieService.getMovieShow(movieId);
        return ResponseEntity.ok(shows);
    }
}
