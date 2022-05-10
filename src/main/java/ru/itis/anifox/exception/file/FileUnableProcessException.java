package ru.itis.anifox.exception.file;

public class FileUnableProcessException extends UnableProcessAniFoxException{
    public FileUnableProcessException(String filename) {
        super(String.format("Cannot process file with name %s", filename));
    }
}
