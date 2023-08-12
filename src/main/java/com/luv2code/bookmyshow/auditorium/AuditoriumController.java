package com.luv2code.bookmyshow.auditorium;

import com.luv2code.bookmyshow.show.ShowResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auditoriums")
public class AuditoriumController {
    private final AuditoriumService auditoriumService;

    @PostMapping()
    ResponseEntity<AuditoriumResponseDTO> addAuditorium(@RequestBody AuditoriumRequestDTO auditorium) {
        AuditoriumResponseDTO auditoriumResponseDTO = auditoriumService.addAuditorium(auditorium);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+auditoriumResponseDTO.id())
                .build()
                .toUri()
        ).body(auditoriumResponseDTO);
    }

    @GetMapping("/{auditoriumId}/shows")
    ResponseEntity<Set<ShowResponseDTO>> getShowsInAuditorium(@PathVariable Integer auditoriumId) {
        Set<ShowResponseDTO> shows = auditoriumService.getShowsInAuditorium(auditoriumId);
        return ResponseEntity.ok(shows);
    }
}
