package ru.itis.anifox.exception.not_found;

public class AnimeNotFoundException extends NotFoundAniFoxException{
    public AnimeNotFoundException() {
        super("Anime is not found");
    }
}
