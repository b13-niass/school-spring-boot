package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Fonction;
import org.example.odc.web.dto.response.FonctionDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FonctionResponseMapper {
    Fonction toEntity(FonctionDtoResponse fonctionDtoResponse);
    FonctionDtoResponse toDTO(Fonction fonction);
    List<FonctionDtoResponse> toDTOList(List<Fonction> fonctions);
    List<Fonction> toEntityList(List<FonctionDtoResponse> fonctionDtoResponses);
}
