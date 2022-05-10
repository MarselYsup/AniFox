package ru.itis.anifox.exception.file;

import org.springframework.http.HttpStatus;
import ru.itis.anifox.exception.main.AniFoxServiceException;

public class UnableProcessAniFoxException extends AniFoxServiceException {
    public UnableProcessAniFoxException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
