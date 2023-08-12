package com.luv2code.bookmyshow.showseat;

import com.luv2code.bookmyshow.seat.SeatDTO;
import com.luv2code.bookmyshow.show.Show;
import com.luv2code.bookmyshow.show.ShowDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link ShowSeat}
 */
public record ShowSeatResponseDTO(ShowDTO show, List<Pair> seatsStatus) implements Serializable {
    public static ShowSeatResponseDTO fromEntityList(List<ShowSeat> showSeatList) {
        if(showSeatList == null || showSeatList.isEmpty()) return null;
        Show showEntity = showSeatList.get(0).getShow();
        List<Pair> seatStatusList =
        showSeatList.parallelStream().map(showSeat -> new Pair(SeatDTO.fromEntity(showSeat.getSeat()),
                        showSeat.getTicket() != null ? ShowSeatStatus.BOOKED : showSeat.getId() != null ? ShowSeatStatus.BLOCKED : ShowSeatStatus.AVAILABLE
                )
        ).toList();

        return new ShowSeatResponseDTO(ShowDTO.fromEntity(showEntity), seatStatusList);
    }

    public static ShowSeatResponseDTO fromRawObjectList(List<Object[]> rawShowSeatList) {
        if(rawShowSeatList == null || rawShowSeatList.isEmpty()) return null;
        Object[] first = rawShowSeatList.get(0);
        ShowDTO showDTO = new ShowDTO((Integer) first[6], (String) first[7], (LocalDateTime) first[8], (Integer) first[9]);

        List<Pair> seatStatusList = rawShowSeatList.parallelStream()
                .map(objects -> new Pair(
                        new SeatDTO((Integer) objects[0], (String) objects[1], (Integer) objects[2], (Integer) objects[3], (String) objects[4], (Integer) objects[5]),
                        objects[10] == null ? ShowSeatStatus.AVAILABLE : objects[11] == null ? ShowSeatStatus.BLOCKED : ShowSeatStatus.BOOKED
                )).toList();

        return new ShowSeatResponseDTO(showDTO, seatStatusList);
    }

    private record Pair(SeatDTO seat, ShowSeatStatus status) {

    }
}