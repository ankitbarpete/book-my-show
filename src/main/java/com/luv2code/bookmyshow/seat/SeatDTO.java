package com.luv2code.bookmyshow.seat;

public record SeatDTO(Integer seatId, String seatNum, int seatRow, int seatCol, String seatType, int seatTypePrice) {
        public static SeatDTO fromEntity(Seat seat) {
            return new SeatDTO(seat.getId(), seat.getSeatNum(), seat.getSeatRow(), seat.getSeatCol(), 
                    seat.getSeatType().getSeatType(), seat.getSeatType().getPrice());
        }
    }