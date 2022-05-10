package ru.itis.anifox.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.models.FileInfoEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    FileInfoEntity uploadFile(MultipartFile file);
    void downloadFile(String storageName, HttpServletResponse response);

}
