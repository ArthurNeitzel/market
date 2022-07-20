package com.study.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotPossibleException extends RuntimeException{
    public NotPossibleException(final String message) {
        this(message, null);
    }

    public NotPossibleException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
