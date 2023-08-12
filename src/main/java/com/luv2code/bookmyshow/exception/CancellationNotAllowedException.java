package com.luv2code.bookmyshow.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CancellationNotAllowedException extends Throwable {
    public CancellationNotAllowedException(String message) {
        super(message);
    }

}
