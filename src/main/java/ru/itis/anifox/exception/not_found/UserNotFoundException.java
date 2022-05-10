package ru.itis.anifox.exception.not_found;

public class UserNotFoundException extends NotFoundAniFoxException{
    public UserNotFoundException() {
        super("User is not found");
    }
}
