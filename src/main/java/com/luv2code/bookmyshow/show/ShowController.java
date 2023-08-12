package com.luv2code.bookmyshow.show;

import com.luv2code.bookmyshow.exception.NotEnoughFeaturesException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;
    @PostMapping
    ResponseEntity<ShowResponseDTO> add(@RequestBody ShowRequestDTO showRequestDTO) {
        ResponseEntity<ShowResponseDTO> result;
        try {
            ShowResponseDTO show = ShowResponseDTO.fromEntity(showService.add(showRequestDTO));
            result = ResponseEntity
                    .created(ServletUriComponentsBuilder.fromCurrentRequest().build("/"+show.id()))
                    .body(show);
        } catch (NotEnoughFeaturesException e) {
            result = ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        } catch (EntityNotFoundException e) {
            result = ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }

        return result;
    }

    @GetMapping
    ResponseEntity<Slice<ShowResponseDTO>> getShowsByCityIdAndMovieId(
            @RequestParam("cityId") Integer cityId, @RequestParam("movieId") Integer movieId,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            Slice<Show> shows = showService.getShowsByCityIdAndMovieId(cityId, movieId, pageNo, pageSize);
            return ResponseEntity.ok(shows.map(ShowResponseDTO::fromEntity));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage())).build();
        }
    }
}
