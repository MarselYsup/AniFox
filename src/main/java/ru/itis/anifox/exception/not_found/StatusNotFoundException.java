package ru.itis.anifox.exception.not_found;

import ru.itis.anifox.exception.main.AniFoxServiceException;

public class StatusNotFoundException extends NotFoundAniFoxException {
    public StatusNotFoundException() {
        super("Anime status not found");
    }
}
