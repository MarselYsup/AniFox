package ru.itis.anifox.exception.already_exist;

import org.springframework.http.HttpStatus;
import ru.itis.anifox.exception.main.AniFoxServiceException;

public class AlreadyExistAniFoxException extends AniFoxServiceException {
    public AlreadyExistAniFoxException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
