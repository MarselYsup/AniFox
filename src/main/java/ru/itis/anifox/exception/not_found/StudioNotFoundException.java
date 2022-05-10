package ru.itis.anifox.exception.not_found;

public class StudioNotFoundException extends NotFoundAniFoxException{
    public StudioNotFoundException() {
        super("Studio is not found");
    }
}
