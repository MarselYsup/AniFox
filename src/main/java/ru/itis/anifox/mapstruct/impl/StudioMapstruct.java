package ru.itis.anifox.mapstruct.impl;

import org.springframework.stereotype.Component;
import ru.itis.anifox.dto.AddStudioDto;
import ru.itis.anifox.mapstruct.BaseMapstruct;
import ru.itis.anifox.models.StudioEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudioMapstruct implements BaseMapstruct<AddStudioDto, StudioEntity> {
    @Override
    public StudioEntity convertTo(AddStudioDto studioDto) {
        return StudioEntity.builder()
                .name(studioDto.getName())
                .build();
    }

    @Override
    public AddStudioDto convertFrom(StudioEntity studioEntity) {
        return AddStudioDto.builder()
                .name(studioEntity.getName())
                .build();
    }

    @Override
    public List<StudioEntity> convertTo(List<AddStudioDto> studioDtos) {
        return studioDtos.stream().map(this::convertTo).collect(Collectors.toList());
    }

    @Override
    public List<AddStudioDto> convertFrom(List<StudioEntity> studioEntities) {
        return studioEntities.stream().map(this::convertFrom).collect(Collectors.toList());
    }
}
