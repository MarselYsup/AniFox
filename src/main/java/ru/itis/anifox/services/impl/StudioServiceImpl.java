package ru.itis.anifox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.dto.AddStudioDto;
import ru.itis.anifox.mapstruct.impl.StudioMapstruct;
import ru.itis.anifox.models.FileInfoEntity;
import ru.itis.anifox.models.StudioEntity;
import ru.itis.anifox.repositories.StudioRepository;
import ru.itis.anifox.services.FileService;
import ru.itis.anifox.services.StudioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudioServiceImpl implements StudioService {

    private final FileService fileService;

    private final StudioRepository studioRepository;

    private final StudioMapstruct studioMapstruct;
    @Override
    public List<AddStudioDto> getAllStudio() {
        return studioMapstruct.convertFrom(studioRepository.findAll());
    }

    @Override
    public void addStudio(MultipartFile multipartFile, AddStudioDto studioDto) {
        FileInfoEntity fileInfoEntity = fileService.uploadFile(multipartFile);
        StudioEntity studioEntity = studioMapstruct.convertTo(studioDto);
        studioEntity.setAvatar(fileInfoEntity);
        studioRepository.save(studioEntity);
    }
}
