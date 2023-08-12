package com.luv2code.bookmyshow.showseat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    /**
     * This method is used to find show seats by show id.
     * */
    @Query("""
                SELECT s.id AS seatId, s.seatNum, s.seatRow, s.seatCol, s.seatType.seatType, s.seatType.price,
                sw.id AS showId, sw.showName, sw.showTime, sw.additionalPrice,
                ss.id, ss.ticket.id, s.seatStatus
                FROM Seat s
                JOIN Show sw ON s.auditorium.id = sw.auditorium.id
                LEFT JOIN ShowSeat ss ON s.id = ss.seat.id AND sw.id = ss.show.id
                WHERE sw.id = :showId
                AND (s.seatStatus is null OR s.seatStatus = 0)
            """)
    List<Object[]> findSeatsForShowByShowId(@Param("showId") Integer id);

    @Query("select (count(s) > 0) from ShowSeat s where s.seat.id in :seatIds and s.show.id = :showId")
    boolean existsBySeatIdInAndShowId(@Param("seatIds") Collection<Integer> seatIds, @Param("showId") Integer showId);

    @Transactional
    @Modifying
    @Query("delete from ShowSeat s where s.createdTime < :createdTime and s.ticket is null")
    int deleteByCreatedTimeBefore(LocalDateTime createdTime);


    /**
     * This method is used to find show seats by show id and seat ids which are not booked.
     * */
    @Query("select s from ShowSeat s where s.show.id = :showId and s.seat.id in :seatIds and s.ticket is null")
    Set<ShowSeat> findByShowIdAndSeatIdInAndTicketIsNull(@Param("showId") Integer showId, @Param("seatIds") Collection<Integer> seatIds);

}