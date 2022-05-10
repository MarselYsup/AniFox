package ru.itis.anifox.exception.not_found;

import org.springframework.http.HttpStatus;
import ru.itis.anifox.exception.main.AniFoxServiceException;

public class NotFoundAniFoxException extends AniFoxServiceException {
    public NotFoundAniFoxException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
