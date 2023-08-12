package com.luv2code.bookmyshow.showseat;

import com.luv2code.bookmyshow.exception.SeatShowAlreadyBlockedException;
import com.luv2code.bookmyshow.seat.Seat;
import com.luv2code.bookmyshow.seat.SeatService;
import com.luv2code.bookmyshow.show.Show;
import com.luv2code.bookmyshow.show.ShowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ShowSeatService {

    private final ShowSeatRepository showSeatRepository;
    private final ShowService showService;
    private final SeatService seatService;

    public ShowSeatResponseDTO getSeatsForShowByShowId(Integer showId) {
        List<Object[]> showSeatsRaw = showSeatRepository.findSeatsForShowByShowId(showId);
        System.out.println(showSeatsRaw.size());
        return ShowSeatResponseDTO.fromRawObjectList(showSeatsRaw);
    }


    @Transactional()
    public List<ShowSeat> blockShowSeats(ShowSeatRequestDTO showSeatRequestDTO) throws SeatShowAlreadyBlockedException {

        if(showSeatRequestDTO == null || showSeatRequestDTO.showId() == null || showSeatRequestDTO.seatIds() == null) {
            throw new IllegalArgumentException();
        }
        boolean exist = showSeatRepository.existsBySeatIdInAndShowId(showSeatRequestDTO.seatIds(),
                showSeatRequestDTO.showId());
        if (exist) throw new SeatShowAlreadyBlockedException("One or more selected seat are already blocked for show");

        Show show = showService.getShowById(showSeatRequestDTO.showId());
        return showSeatRequestDTO.seatIds().stream().map(
                seatId -> {
                    Seat seat = seatService.getSeatById(seatId);
                    ShowSeat showSeat = new ShowSeat();
                    showSeat.setSeat(seat);
                    showSeat.setShow(show);
                    return showSeatRepository.save(showSeat);
                }
        ).toList();
    }

    /**
     * This method will return all show seats for given show id and seat ids, which are not booked.
     * @param showId, Integer as input
     * @param seatIds, List<Integer> as input
     * @return Set<ShowSeat>
     */
    public Set<ShowSeat> getNotBookedShowSeatsByShowIdAndSeatIds(Integer showId, List<Integer> seatIds) {
        return showSeatRepository.findByShowIdAndSeatIdInAndTicketIsNull(showId, seatIds);
    }
}
