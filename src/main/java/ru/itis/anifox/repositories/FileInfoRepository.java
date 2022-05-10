package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.FileInfoEntity;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfoEntity, Long> {
    Optional<FileInfoEntity> findByStorageFileName(String storageName);
}
