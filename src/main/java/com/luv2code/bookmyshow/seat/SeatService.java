package com.luv2code.bookmyshow.seat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.luv2code.bookmyshow.auditorium.Auditorium;
import com.luv2code.bookmyshow.auditorium.AuditoriumRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    private final SeatTypeRepository seatTypeRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final SeatRepository seatRepository;
    private final ObjectMapper objectMapper;

    public SeatService(SeatTypeRepository seatTypeRepository,
                       AuditoriumRepository auditoriumRepository,
                       SeatRepository seatRepository) {
        this.seatTypeRepository = seatTypeRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public SeatResponseDTO add(SeatRequestDTO seatRequestDTO) {
        Seat seat = seatRequestDTO.toEntity();
        SeatType seatType = seatTypeRepository.findById(seatRequestDTO.seatTypeId()).orElseThrow();
        Auditorium auditorium = auditoriumRepository.findById(seatRequestDTO.auditoriumId()).orElseThrow();
        seat.setSeatType(seatType);
        seat.setAuditorium(auditorium);
        Seat savedSeat = seatRepository.save(seat);
        return SeatResponseDTO.fromEntity(savedSeat);
    }


    public SeatResponseDTO patch(Integer id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Seat seat = seatRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        JsonNode patched = patch.apply(objectMapper.convertValue(seat, JsonNode.class));
        Seat updatedSeat = objectMapper.treeToValue(patched, Seat.class);
        return SeatResponseDTO.fromEntity(seatRepository.save(updatedSeat));
    }

    public Seat getSeatById(Integer seatId) {
        return seatRepository.findById(seatId).orElseThrow(EntityNotFoundException::new);
    }
}
