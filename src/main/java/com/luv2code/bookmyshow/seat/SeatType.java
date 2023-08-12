package com.luv2code.bookmyshow.seat;

import com.luv2code.bookmyshow.generic.GenericEntity;
import com.luv2code.bookmyshow.user.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class SeatType extends GenericEntity {
    private String seatType;
    private int price;

    private SeatType(SeatTypeBuilder builder) {
        this.seatType = builder.seatType;
        this.price = builder.price;
        this.setCreatedBy(builder.createdBy);
    }

    private static class SeatTypeBuilder{
        public User createdBy;
        private String seatType;
        private int price;
        public SeatTypeBuilder seatType(String seatType) {
            this.seatType = seatType;
            return this;
        }

        public SeatTypeBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public SeatTypeBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }
        public SeatType build() {
            if(seatType == null || seatType.isBlank() || seatType.isEmpty()) {
                throw new IllegalArgumentException(
                        "seatType argument is required!");
            }
            if (price == 0) {
                throw new IllegalArgumentException("price can not be zero!");
            }

            return new SeatType(this);
        }
    }
}
