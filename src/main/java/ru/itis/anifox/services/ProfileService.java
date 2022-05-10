package ru.itis.anifox.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.models.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface ProfileService {

    UserEntity setAvatar(MultipartFile file, String email);

    UserEntity getUserByEmail(String email);

}
