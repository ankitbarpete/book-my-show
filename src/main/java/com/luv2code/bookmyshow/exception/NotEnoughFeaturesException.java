package com.luv2code.bookmyshow.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotEnoughFeaturesException extends Throwable {
    public NotEnoughFeaturesException(String message) {
        super(message);
    }
}
