package ru.itis.anifox.mapstruct.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.anifox.dto.GenreDto;
import ru.itis.anifox.mapstruct.BaseMapstruct;
import ru.itis.anifox.models.GenreEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreMapstruct implements BaseMapstruct<GenreDto, GenreEntity> {
    @Override
    public GenreEntity convertTo(GenreDto genreDto) {
        return GenreEntity.builder()
                .name(genreDto.getGenre())
                .build();
    }

    @Override
    public GenreDto convertFrom(GenreEntity genreEntity) {
        return GenreDto.builder()
                .genre(genreEntity.getName())
                .build();
    }

    @Override
    public List<GenreEntity> convertTo(List<GenreDto> genreDtos) {
        return genreDtos.stream().map(this::convertTo).collect(Collectors.toList());
    }

    @Override
    public List<GenreDto> convertFrom(List<GenreEntity> genreEntities) {
        return genreEntities.stream().map(this::convertFrom).collect(Collectors.toList());
    }
}
