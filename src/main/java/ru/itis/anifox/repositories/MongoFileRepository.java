package ru.itis.anifox.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.anifox.models.MongoFileEntity;

import java.util.UUID;

public interface MongoFileRepository extends MongoRepository<MongoFileEntity, UUID> {
}
