package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.web.dto.response.RefOnlyDtoResponse;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface RefOnlyMapper {
    Referentiel toEntity(RefOnlyDtoResponse refOnlyDtoResponse);
    RefOnlyDtoResponse toDTO(Referentiel referentiel);
    Collection<RefOnlyDtoResponse> toDTOList(Collection<Referentiel> referentiels);
    Collection<Referentiel> toEntityList(Collection<RefOnlyDtoResponse> referentielDtoResponses);
}