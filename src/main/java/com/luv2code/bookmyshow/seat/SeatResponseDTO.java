package com.luv2code.bookmyshow.seat;

import java.io.Serializable;

/**
 * DTO for {@link Seat}
 */
public record SeatResponseDTO(Integer id, String seatNum, int seatRow, int seatCol, Integer seatTypeId,
                              String seatTypeSeatType, int seatTypePrice,
                              SeatStatus seatStatus) implements Serializable {

    public static SeatResponseDTO fromEntity(Seat seat) {
        return new SeatResponseDTO(seat.getId(),
                seat.getSeatNum(),
                seat.getSeatRow(),
                seat.getSeatRow(),
                seat.getSeatType().getId(),
                seat.getSeatType().getSeatType(),
                seat.getSeatType().getPrice(),
                seat.getSeatStatus()
        );
    }
}