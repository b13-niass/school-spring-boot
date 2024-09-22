package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Emargement;
import org.example.odc.web.dto.response.EmargementDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmargementResponseMapper {
    Emargement toEntity(EmargementDtoResponse emargementDtoResponse);
    EmargementDtoResponse toDTO(Emargement emargement);
    List<EmargementDtoResponse> toDTOList(List<Emargement> emargements);
    List<Emargement> toEntityList(List<EmargementDtoResponse> emargementDtoResponses);
}
