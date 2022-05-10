package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByConfirmCode(String confirmCode);

    boolean existsByEmail(String email);
}
