package ru.itis.anifox.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.anifox.dto.AddStudioDto;

import java.util.List;

public interface StudioService {
    List<AddStudioDto> getAllStudio();
    void addStudio(MultipartFile multipartFile, AddStudioDto studioDto);
}
