package ru.itis.anifox.exception.already_exist;

import org.springframework.http.HttpStatus;

public class MailAlreadyExistException extends AlreadyExistAniFoxException{
    public MailAlreadyExistException() {
        super("Mail already exist!");
    }
}
