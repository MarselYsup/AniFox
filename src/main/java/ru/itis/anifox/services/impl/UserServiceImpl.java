package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.anifox.exception.not_found.UserNotFoundException;
import ru.itis.anifox.models.UserEntity;
import ru.itis.anifox.repositories.UserRepository;
import ru.itis.anifox.services.UserService;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserEntityByMail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
