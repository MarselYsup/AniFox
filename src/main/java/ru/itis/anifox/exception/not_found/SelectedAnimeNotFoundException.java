package ru.itis.anifox.exception.not_found;

public class SelectedAnimeNotFoundException extends NotFoundAniFoxException{
    public SelectedAnimeNotFoundException() {
        super("Selected anime not found");
    }
}
