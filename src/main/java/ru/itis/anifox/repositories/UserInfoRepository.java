package ru.itis.anifox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.anifox.models.UserInfoEntity;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
}
