package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.AnimeEntity;

public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {
}
