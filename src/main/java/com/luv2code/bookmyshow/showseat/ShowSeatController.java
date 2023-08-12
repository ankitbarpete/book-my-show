package com.luv2code.bookmyshow.showseat;

import com.luv2code.bookmyshow.exception.SeatShowAlreadyBlockedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/show-seats")
public class ShowSeatController {
    private final ShowSeatService showSeatService;

    @GetMapping()
    ResponseEntity<ShowSeatResponseDTO> getSeatsForShow(@RequestParam("showId") Integer showId) {
        ShowSeatResponseDTO seatsForShow = showSeatService.getSeatsForShowByShowId(showId);
        return ResponseEntity.ok(seatsForShow);
    }

    @PostMapping("/block")
    ResponseEntity<ShowSeatResponseDTO> blockSeats(@RequestBody ShowSeatRequestDTO showSeatRequestDTO) {
        try {
            List<ShowSeat> blockedShowSeats = showSeatService.blockShowSeats(showSeatRequestDTO);
            ShowSeatResponseDTO showSeatDto = ShowSeatResponseDTO.fromEntityList(blockedShowSeats);
            return ResponseEntity.accepted().body(showSeatDto);
        } catch (DataIntegrityViolationException | SeatShowAlreadyBlockedException e) {
            log.error("error occurred", e);
            return ResponseEntity.of(ProblemDetail
                    .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                            e.getMessage())).build();
        } catch (EntityNotFoundException e) {
            log.error("error occurred", e);
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }
    }

}
