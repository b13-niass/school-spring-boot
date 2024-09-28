package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Apprenant;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApprenantResponseMapper {
    Apprenant toEntity(ApprenantResponse apprenantDtoResponse);
    ApprenantResponse toDTO(Apprenant apprenant);
    List<ApprenantResponse> toDTOList(List<Apprenant> apprenants);
    List<Apprenant> toEntityList(List<ApprenantResponse> apprenantDtoResponses);
}
