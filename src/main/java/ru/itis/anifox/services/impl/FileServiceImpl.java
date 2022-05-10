package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.exception.file.FileUnableProcessException;
import ru.itis.anifox.exception.not_found.FileNotFoundException;
import ru.itis.anifox.models.FileInfoEntity;
import ru.itis.anifox.models.MongoFileEntity;
import ru.itis.anifox.repositories.FileInfoRepository;
import ru.itis.anifox.repositories.MongoFileRepository;
import ru.itis.anifox.services.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;

    private final MongoFileRepository mongoFileRepository;

    private final static String STORAGE_PATH = "/file/";

    @Override
    public FileInfoEntity uploadFile(MultipartFile file) {
        try {
            MongoFileEntity mongoFile = MongoFileEntity.builder()
                    .id(UUID.randomUUID())
                    .content(new Binary(file.getBytes())).build();

            mongoFile = mongoFileRepository.save(mongoFile);

            FileInfoEntity fileInfo  = FileInfoEntity
                    .builder()
                    .originalFileName(file.getOriginalFilename())
                    .storageFileName(UUID.randomUUID().toString())
                    .mimeType(file.getContentType())
                    .size(file.getSize())
                    .imageId(mongoFile.getId())
                    .build();

            fileInfo = fileInfoRepository.save(fileInfo);
            return fileInfo;
        }catch (IOException exception) {
            throw new FileUnableProcessException(file.getOriginalFilename());
        }
    }

    @Override
    public void downloadFile(String storageName, HttpServletResponse response) {
        try {
            FileInfoEntity fileInfo = fileInfoRepository.findByStorageFileName(storageName)
                    .orElseThrow(FileNotFoundException::new);
            MongoFileEntity fileEntity = getFileData(fileInfo);
            response.getOutputStream().write(fileEntity.getContent().getData());
            response.setContentLengthLong(fileInfo.getSize());
            response.setContentType(fileInfo.getMimeType());
            response.setHeader("Content-Disposition",
                    String.format("filename=\"%s\"", fileInfo.getOriginalFileName()));
        } catch (IOException e){
            throw new FileNotFoundException();
        }

    }

    private MongoFileEntity getFileData(FileInfoEntity fileInfo) {
        return mongoFileRepository.findById(fileInfo.getImageId())
                .orElseThrow(FileNotFoundException::new);
    }

}
