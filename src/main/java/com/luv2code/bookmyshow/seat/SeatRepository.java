package com.luv2code.bookmyshow.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Transactional
    @Modifying
    @Query("update Seat s set s.seatType = ?3, s.seatStatus = ?2 where s.id = ?1")
    void updateSeatTypeAndSeatStatusById(Integer id, SeatStatus seatStatus, SeatType seatType);

    Set<Seat> findByAuditoriumIdAndSeatStatus(Integer id, SeatStatus seatStatus);
}