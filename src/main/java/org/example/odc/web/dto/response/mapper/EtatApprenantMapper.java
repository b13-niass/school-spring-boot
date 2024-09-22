package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.EtatApprenant;
import org.example.odc.web.dto.response.EtatApprenantDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EtatApprenantMapper {
    EtatApprenant toEntity(EtatApprenantDtoResponse etatApprenantDtoResponse);
    EtatApprenantDtoResponse toDTO(EtatApprenant etatApprenant);
    List<EtatApprenantDtoResponse> toDTOList(List<EtatApprenant> etatApprenants);
    List<EtatApprenant> toEntityList(List<EtatApprenantDtoResponse> etatApprenantDtoResponses);
}
