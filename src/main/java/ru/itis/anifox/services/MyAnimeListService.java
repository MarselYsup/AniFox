package ru.itis.anifox.services;

import ru.itis.anifox.dto.SelectAnimeDto;
import ru.itis.anifox.dto.WatchedAnimeDto;
import ru.itis.anifox.models.AnimeEntity;
import ru.itis.anifox.models.SelectedAnimeEntity;
import ru.itis.anifox.models.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MyAnimeListService {

    void addAnimeToList(SelectAnimeDto animeDto, HttpServletRequest request);


    List<WatchedAnimeDto> getWatchedAnimeList(HttpServletRequest request);
}
