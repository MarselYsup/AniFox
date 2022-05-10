package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.anifox.dto.GenreDto;
import ru.itis.anifox.mapstruct.impl.GenreMapstruct;
import ru.itis.anifox.repositories.GenreRepository;
import ru.itis.anifox.services.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapstruct genreMapstruct;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreMapstruct.convertFrom(genreRepository.findAll());
    }
}
