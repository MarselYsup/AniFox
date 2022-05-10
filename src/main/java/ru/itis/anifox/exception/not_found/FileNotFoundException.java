package ru.itis.anifox.exception.not_found;

public class FileNotFoundException extends NotFoundAniFoxException{
    public FileNotFoundException() {
        super("File is not found");
    }
}
