package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.dto.AddAnimeDto;
import ru.itis.anifox.dto.AnimeDto;
import ru.itis.anifox.exception.not_found.AnimeNotFoundException;
import ru.itis.anifox.exception.not_found.SelectedAnimeNotFoundException;
import ru.itis.anifox.exception.not_found.StatusNotFoundException;
import ru.itis.anifox.mapstruct.impl.AddAnimeMapstruct;
import ru.itis.anifox.mapstruct.impl.AnimeMapstruct;
import ru.itis.anifox.models.AnimeEntity;
import ru.itis.anifox.models.FileInfoEntity;
import ru.itis.anifox.models.SelectedAnimeEntity;
import ru.itis.anifox.models.UserEntity;
import ru.itis.anifox.repositories.AnimeRepository;
import ru.itis.anifox.repositories.MyAnimeListRepository;
import ru.itis.anifox.services.AnimeService;
import ru.itis.anifox.services.FileService;
import ru.itis.anifox.services.MyAnimeListService;
import ru.itis.anifox.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;

    private final AddAnimeMapstruct addAnimeMapstruct;

    private final AnimeMapstruct animeMapstruct;

    private final MyAnimeListRepository myAnimeListRepository;

    private final UserService userService;

    private final FileService fileService;

    @Override
    public void addAnime(MultipartFile avatar, AddAnimeDto animeDto) {
        FileInfoEntity fileInfoEntity = fileService.uploadFile(avatar);
        AnimeEntity animeEntity = addAnimeMapstruct.convertTo(animeDto);
        animeEntity.setAvatar(fileInfoEntity);
        animeRepository.save(animeEntity);
    }

    @Override
    public List<AnimeDto> getAllAnime() {
        return animeMapstruct.convertFrom(animeRepository.findAll());
    }

    @Override
    public AnimeDto getAnime(Long animeId) {
        return animeMapstruct.convertFrom(animeRepository.findById(animeId)
        .orElseThrow(AnimeNotFoundException::new));
    }

    @Override
    public AnimeEntity getAnimeEntityById(Long animeId) {
        return animeRepository.findById(animeId).orElseThrow(AnimeNotFoundException::new);
    }

    @Override
    public Integer getStatus(Long animeId, HttpServletRequest request) {
        AnimeEntity animeEntity = animeRepository.getById(animeId);
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        userEntity = userService.getUserEntityByMail(userEntity.getEmail());
        if(!myAnimeListRepository.existsByAnimeEntityAndUserEntity(animeEntity, userEntity)) return 0;
        SelectedAnimeEntity selectedAnimeEntity = myAnimeListRepository
                .getByAnimeEntityAndUserEntity(animeEntity, userEntity);
        switch (selectedAnimeEntity.getStatus()) {
            case WATCHING:
                return 1;
            case WATCHED:
                return 2;
            case PLAN_TO_WATCH:
                return 3;
            default:
                return 0;
        }
    }


}
