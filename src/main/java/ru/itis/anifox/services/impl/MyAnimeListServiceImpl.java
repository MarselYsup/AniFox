package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.anifox.dto.SelectAnimeDto;
import ru.itis.anifox.dto.WatchedAnimeDto;
import ru.itis.anifox.exception.not_found.AnimeNotFoundException;
import ru.itis.anifox.exception.not_found.SelectedAnimeNotFoundException;
import ru.itis.anifox.exception.not_found.StatusNotFoundException;
import ru.itis.anifox.models.AnimeEntity;
import ru.itis.anifox.models.SelectedAnimeEntity;
import ru.itis.anifox.models.UserEntity;
import ru.itis.anifox.models.enums.Status;
import ru.itis.anifox.repositories.MyAnimeListRepository;
import ru.itis.anifox.services.AnimeService;
import ru.itis.anifox.services.MyAnimeListService;
import ru.itis.anifox.services.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.anifox.models.enums.Status.*;
import static ru.itis.anifox.models.enums.Status.PLAN_TO_WATCH;
import static ru.itis.anifox.models.enums.Status.WATCHED;

@Service
@RequiredArgsConstructor
public class MyAnimeListServiceImpl implements MyAnimeListService {

    private final MyAnimeListRepository myAnimeListRepository;

    private final AnimeService animeService;

    private final UserService userService;

    @Override
    public void addAnimeToList(SelectAnimeDto animeDto, HttpServletRequest request) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        userEntity = userService.getUserEntityByMail(userEntity.getEmail());
        AnimeEntity animeEntity = animeService.getAnimeEntityById(animeDto.getAnimeId());
        SelectedAnimeEntity selectedAnimeEntity = myAnimeListRepository
                .findByAnimeEntityAndUserEntity(animeEntity, userEntity)
                .orElse(SelectedAnimeEntity.builder()
                        .animeEntity(animeEntity)
                        .userEntity(userEntity)
                        .build());

        switch (animeDto.getStatus()) {
            case 1:
                selectedAnimeEntity.setStatus(WATCHING);
                break;
            case 2:
                selectedAnimeEntity.setStatus(WATCHED);
                break;
            case 3:
                selectedAnimeEntity.setStatus(PLAN_TO_WATCH);
                break;
            default:
                throw new StatusNotFoundException();

        }
        myAnimeListRepository.save(selectedAnimeEntity);
    }

    @Override
    public List<WatchedAnimeDto> getWatchedAnimeList(HttpServletRequest request) {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");
        userEntity = userService.getUserEntityByMail(userEntity.getEmail());
        List<SelectedAnimeEntity> selectedAnimeEntityList = myAnimeListRepository.getAllByUserEntity(userEntity);
        return selectedAnimeEntityList.stream()
                .map(e -> WatchedAnimeDto.builder()
                        .animeId(e.getAnimeEntity().getId())
                        .rating(e.getRating())
                        .status(getStatusNumber(e.getStatus()))
                        .title(e.getAnimeEntity().getTitle())
                        .build())
                .sorted(Comparator.comparing(WatchedAnimeDto::getTitle))
                .collect(Collectors.toList());
    }

    private int getStatusNumber(Status status) {
        switch (status) {
            case WATCHING:
                return 1;
            case WATCHED:
                return 2;
            case PLAN_TO_WATCH:
                return 3;
            default:
                throw new StatusNotFoundException();

        }
    }

}
