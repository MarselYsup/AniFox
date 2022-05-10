package ru.itis.anifox.exception.not_found;

public class GenreNotFoundException extends NotFoundAniFoxException{
    public GenreNotFoundException() {
        super("Genre is not found");
    }
}
