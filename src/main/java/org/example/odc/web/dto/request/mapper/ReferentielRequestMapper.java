package org.example.odc.web.dto.request.mapper;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.web.dto.request.ReferentielDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReferentielRequestMapper {
    ReferentielRequestMapper INSTANCE = Mappers.getMapper(ReferentielRequestMapper.class);

    // Map the request DTO to an entity or another DTO (depending on your use case)
    @Mapping(source = "competences", target = "competences")
    Referentiel toEntity(ReferentielDTORequest referentielRequestDTO);

    // Reverse mapping if needed
    ReferentielDTORequest toDto(Referentiel referentiel);
}
