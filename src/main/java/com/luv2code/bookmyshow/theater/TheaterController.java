package com.luv2code.bookmyshow.theater;

import com.luv2code.bookmyshow.auditorium.AuditoriumResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/theaters"))
public class TheaterController {


    private final TheaterService theaterService;

    @PostMapping
    ResponseEntity<TheaterResponseDTO> addTheater(@RequestBody TheaterRequestDTO theater) {
        TheaterResponseDTO savedTheater = theaterService.save(theater);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/"+savedTheater.id()).build().toUri())
                .body(savedTheater);
    }

    @GetMapping("/{theaterId}")
    ResponseEntity<TheaterResponseDTO> getTheaterById(@PathVariable Integer theaterId) {
        TheaterResponseDTO theater = theaterService.getTheaterById(theaterId);
        return ResponseEntity.ok(theater);
    }

    @GetMapping("/{theaterId}/auditoriums")
    ResponseEntity<Set<AuditoriumResponseDTO>> getAuditoriumsInTheater(@PathVariable Integer theaterId) {
        Set<AuditoriumResponseDTO> auditoriums = theaterService.getAuditoriumsInTheater(theaterId);
        return ResponseEntity.ok(auditoriums);
    }
}
