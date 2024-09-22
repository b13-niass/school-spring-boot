package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.web.dto.response.PromoReferentielDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromoReferentielResponseMapper {
    PromoReferentiel toEntity(PromoReferentielDtoResponse promoReferentielDtoResponse);
    PromoReferentielDtoResponse toDTO(PromoReferentiel promoReferentiel);
    List<PromoReferentielDtoResponse> toDTOList(List<PromoReferentiel> promoReferentiels);
    List<PromoReferentiel> toEntityList(List<PromoReferentielDtoResponse> promoReferentielDtoResponses);
}
