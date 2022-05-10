package ru.itis.anifox.services;

import ru.itis.anifox.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();
}
