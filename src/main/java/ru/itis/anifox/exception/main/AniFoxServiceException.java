package ru.itis.anifox.exception.main;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AniFoxServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public AniFoxServiceException(HttpStatus httpStatus,String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
