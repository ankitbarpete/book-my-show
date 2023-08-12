package com.luv2code.bookmyshow.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TicketPriceCalculationException extends Throwable {
    public TicketPriceCalculationException(String message) {
        super(message);
    }
}
