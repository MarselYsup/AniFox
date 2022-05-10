package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.StudioEntity;

import java.util.Optional;

public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
    Optional<StudioEntity> findByName(String name);
}
