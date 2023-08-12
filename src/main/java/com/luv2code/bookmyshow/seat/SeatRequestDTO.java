package com.luv2code.bookmyshow.seat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Seat}
 */
public record SeatRequestDTO(String seatNum, int seatRow, int seatCol, Integer seatTypeId,
                             Integer auditoriumId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SeatRequestDTO) obj;
        return Objects.equals(this.seatNum, that.seatNum) &&
                this.seatRow == that.seatRow &&
                this.seatCol == that.seatCol &&
                Objects.equals(this.seatTypeId, that.seatTypeId) &&
                Objects.equals(this.auditoriumId, that.auditoriumId);
    }

    @Override
    public String toString() {
        return "SeatRequestDTO[" +
                "seatNum=" + seatNum + ", " +
                "seatRow=" + seatRow + ", " +
                "seatCol=" + seatCol + ", " +
                "seatTypeId=" + seatTypeId + ", " +
                "auditoriumId=" + auditoriumId + ']';
    }

    public Seat toEntity() {
        Seat seat = new Seat();
        seat.setSeatNum(seatNum);
        seat.setSeatRow(seatRow);
        seat.setSeatCol(seatCol);
        seat.setSeatStatus(SeatStatus.AVAILABLE);
        return seat;
    }
}