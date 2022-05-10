package ru.itis.anifox.mapstruct.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.anifox.dto.AddAnimeDto;
import ru.itis.anifox.dto.GenreDto;
import ru.itis.anifox.exception.not_found.GenreNotFoundException;
import ru.itis.anifox.exception.not_found.StudioNotFoundException;
import ru.itis.anifox.mapstruct.BaseMapstruct;
import ru.itis.anifox.models.AnimeEntity;
import ru.itis.anifox.models.GenreEntity;
import ru.itis.anifox.repositories.GenreRepository;
import ru.itis.anifox.repositories.StudioRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddAnimeMapstruct implements BaseMapstruct<AddAnimeDto, AnimeEntity> {
    private final GenreRepository genreRepository;

    private final StudioRepository studioRepository;

    @Override
    public AnimeEntity convertTo(AddAnimeDto animeDto) {
        AnimeEntity animeEntity = AnimeEntity.builder()
                .countOfEpisodes(animeDto.getCountOfEpisodes())
                .description(animeDto.getDescription())
                .rating(0)
                .yearOfCreation(animeDto.getYearOfCreation())
                .title(animeDto.getTitle())
                .youtubeUrl(animeDto.getYoutubeUrl())
                .build();

        animeEntity.setStudio(studioRepository.findByName(animeDto.getStudioTitle())
                .orElseThrow(StudioNotFoundException::new));
        animeEntity.setGenreSet(new HashSet<>());
        for(GenreDto genre: animeDto.getGenres()) {
            animeEntity.getGenreSet().add(genreRepository.findByName(genre.getGenre())
                    .orElseThrow(GenreNotFoundException::new));
        }
        return animeEntity;
    }

    @Override
    public AddAnimeDto convertFrom(AnimeEntity animeEntity) {
        AddAnimeDto animeDto = AddAnimeDto.builder()
                .countOfEpisodes(animeEntity.getCountOfEpisodes())
                .description(animeEntity.getDescription())
                .title(animeEntity.getTitle())
                .youtubeUrl(animeEntity.getYoutubeUrl())
                .yearOfCreation(animeEntity.getYearOfCreation())
                .studioTitle(animeEntity.getStudio().getName())
                .build();
        animeDto.setGenres(new ArrayList<>());

        for (GenreEntity genreEntity : animeEntity.getGenreSet()) {
            animeDto.getGenres().add(new GenreDto(genreEntity.getName()));
        }
        return animeDto;
    }

    @Override
    public List<AnimeEntity> convertTo(List<AddAnimeDto> animeDtos) {
        return animeDtos.stream().map(this::convertTo).collect(Collectors.toList());
    }

    @Override
    public List<AddAnimeDto> convertFrom(List<AnimeEntity> animeEntities) {
        return animeEntities.stream().map(this::convertFrom).collect(Collectors.toList());
    }
}
