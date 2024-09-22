package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Competence;
import org.example.odc.web.dto.response.CompetenceDtoResponse;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring",uses = {ModuleResponseMapper.class})
public interface CompetenceResponseMapper {
    Competence toEntity(CompetenceDtoResponse competenceDtoResponse);
    CompetenceDtoResponse toDTO(Competence competence);
    Collection<CompetenceDtoResponse> toDTOList(Collection<Competence> competences);
    Collection<Competence> toEntityList(Collection<CompetenceDtoResponse> competenceDtoResponses);
}
