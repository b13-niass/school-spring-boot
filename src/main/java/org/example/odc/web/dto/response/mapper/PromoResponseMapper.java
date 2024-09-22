package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Promo;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromoResponseMapper {
    Promo toEntity(PromoDtoResponse promoDtoResponse);
    PromoDtoResponse toDTO(Promo promo);
    List<PromoDtoResponse> toDTOList(List<Promo> promos);
    List<Promo> toEntityList(List<PromoDtoResponse> promoDtoResponses);
}
