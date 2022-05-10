package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.AnimeEntity;
import ru.itis.anifox.models.SelectedAnimeEntity;
import ru.itis.anifox.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface MyAnimeListRepository extends JpaRepository<SelectedAnimeEntity, Long> {

    Optional<SelectedAnimeEntity> findByAnimeEntityAndUserEntity(AnimeEntity animeEntity, UserEntity userEntity);

    boolean existsByAnimeEntityAndUserEntity(AnimeEntity animeEntity, UserEntity userEntity);

    SelectedAnimeEntity getByAnimeEntityAndUserEntity(AnimeEntity animeEntity, UserEntity userEntity);

    List<SelectedAnimeEntity> getAllByUserEntity(UserEntity userEntity);

}
