package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReferentielResponseMapper {
    Referentiel toEntity(ReferentielDtoResponse referentielDtoResponse);
    ReferentielDtoResponse toDTO(Referentiel referentiel);
    List<ReferentielDtoResponse> toDTOList(List<Referentiel> referentiels);
    List<Referentiel> toEntityList(List<ReferentielDtoResponse> referentielDtoResponses);
}
