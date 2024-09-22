package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Apprenant;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApprenantResponseMapper {
    Apprenant toEntity(ApprenantDtoResponse apprenantDtoResponse);
    ApprenantDtoResponse toDTO(Apprenant apprenant);
    List<ApprenantDtoResponse> toDTOList(List<Apprenant> apprenants);
    List<Apprenant> toEntityList(List<ApprenantDtoResponse> apprenantDtoResponses);
}
