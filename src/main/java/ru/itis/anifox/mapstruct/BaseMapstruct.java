package ru.itis.anifox.mapstruct;

import java.util.List;

public interface BaseMapstruct <DTO, ENTITY> {
   ENTITY convertTo(DTO dto);

   DTO convertFrom(ENTITY entity);

   List<ENTITY> convertTo(List<DTO> dtoList);

   List<DTO> convertFrom(List<ENTITY> entityList);
}
