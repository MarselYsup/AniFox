package ru.itis.anifox.services;

import ru.itis.anifox.models.UserEntity;

public interface UserService {
    UserEntity getUserEntityByMail(String email);
}
