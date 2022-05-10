package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.GenreEntity;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    Optional<GenreEntity> findByName(String name);
}
