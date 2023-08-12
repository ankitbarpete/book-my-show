package com.luv2code.bookmyshow.showseat;

import java.util.List;

public record ShowSeatRequestDTO(Integer showId, List<Integer> seatIds) {
}
