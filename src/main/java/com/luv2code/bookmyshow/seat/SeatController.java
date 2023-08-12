package com.luv2code.bookmyshow.seat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/seats")
@Slf4j
public class SeatController {
    private final SeatService seatService;
    @PostMapping
    ResponseEntity<SeatResponseDTO> add(@RequestBody SeatRequestDTO seatRequestDTO) {
        SeatResponseDTO savedSeat = seatService.add(seatRequestDTO);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().build("/"+savedSeat.id()))
                .body(savedSeat);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    ResponseEntity<SeatResponseDTO> update(@PathVariable Integer id, @RequestBody JsonPatch patch){
        SeatResponseDTO updatedSeat;
        try {
            updatedSeat = seatService.patch(id, patch);
            return ResponseEntity.ok(updatedSeat);
        } catch (JsonPatchException | JsonProcessingException e) {
            log.error("Exception Occurred: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
