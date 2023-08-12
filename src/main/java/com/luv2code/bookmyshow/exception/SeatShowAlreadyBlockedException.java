package com.luv2code.bookmyshow.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SeatShowAlreadyBlockedException extends Throwable {
    public SeatShowAlreadyBlockedException(String message) {
        super(message);
    }
}
