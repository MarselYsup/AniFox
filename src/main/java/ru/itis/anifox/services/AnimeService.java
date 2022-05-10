package ru.itis.anifox.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.dto.AddAnimeDto;
import ru.itis.anifox.dto.AnimeDto;
import ru.itis.anifox.models.AnimeEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AnimeService {
    void addAnime(MultipartFile avatar, AddAnimeDto animeDto);

    List<AnimeDto> getAllAnime();

    AnimeDto getAnime(Long animeId);

    AnimeEntity getAnimeEntityById(Long animeId);

    Integer getStatus(Long animeId, HttpServletRequest request);
}
