package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.exception.not_found.UserNotFoundException;
import ru.itis.anifox.models.FileInfoEntity;
import ru.itis.anifox.models.UserEntity;
import ru.itis.anifox.repositories.UserRepository;
import ru.itis.anifox.services.FileService;
import ru.itis.anifox.services.ProfileService;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final FileService fileService;

    private final UserRepository userRepository;

    @Override
    public UserEntity setAvatar(MultipartFile file, String email) {
        FileInfoEntity fileInfoEntity = fileService.uploadFile(file);
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        userEntity.setAvatar(fileInfoEntity);
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
