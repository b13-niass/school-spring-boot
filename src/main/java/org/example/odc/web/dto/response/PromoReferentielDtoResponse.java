package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.PromoReferentiel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PromoReferentielDtoResponse {

    private Long id;

    // Representing the promo and referentiel with simplified fields or their own DTOs
    private PromoDtoResponse promo;
    private ReferentielDtoResponse referentiel;

    // List of apprenants and competences
    private List<ApprenantDtoResponse> apprenants;
    private List<CompetenceDtoResponse> competences;

    // Convert PromoReferentiel entity to DTO
    public static PromoReferentielDtoResponse toDTO(PromoReferentiel promoReferentiel) {
        if (promoReferentiel == null) {
            return null;
        }

        return PromoReferentielDtoResponse.builder()
                .id(promoReferentiel.getId())
                .promo(promoReferentiel.getPromo() != null
                        ? PromoDtoResponse.toDTO(promoReferentiel.getPromo())
                        : null)
                .referentiel(promoReferentiel.getReferentiel() != null
                        ? ReferentielDtoResponse.toDTO(promoReferentiel.getReferentiel())
                        : null)
                .apprenants(promoReferentiel.getApprenants() != null
                        ? promoReferentiel.getApprenants().stream()
                        .map(ApprenantDtoResponse::toDTO)
                        .collect(Collectors.toList())
                        : null)
                .competences(promoReferentiel.getCompetences() != null
                        ? promoReferentiel.getCompetences().stream()
                        .map(CompetenceDtoResponse::toDTO)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

    // Convert DTO back to PromoReferentiel entity
    public static PromoReferentiel toEntity(PromoReferentielDtoResponse promoReferentielDTO) {
        if (promoReferentielDTO == null) {
            return null;
        }

        PromoReferentiel promoReferentiel = new PromoReferentiel();
        promoReferentiel.setId(promoReferentielDTO.getId());
        promoReferentiel.setPromo(promoReferentielDTO.getPromo() != null
                ? PromoDtoResponse.toEntity(promoReferentielDTO.getPromo())
                : null);
        promoReferentiel.setReferentiel(promoReferentielDTO.getReferentiel() != null
                ? ReferentielDtoResponse.toEntity(promoReferentielDTO.getReferentiel())
                : null);
        // Convert apprenants and competences back to entities if needed
        return promoReferentiel;
    }
}
