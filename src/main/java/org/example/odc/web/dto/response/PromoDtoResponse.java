package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Promo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PromoDtoResponse {

    private Long id;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeReel;
    private String etat;

    // List of PromoReferentielDtoResponse (or simply promo referentiel names/codes)
    private List<PromoReferentielDtoResponse> promoReferentiels;

    // Convert Promo entity to DTO
    public static PromoDtoResponse toDTO(Promo promo) {
        if (promo == null) {
            return null;
        }

        return PromoDtoResponse.builder()
                .id(promo.getId())
                .libelle(promo.getLibelle())
                .dateDebut(promo.getDateDebut())
                .dateFin(promo.getDateFin())
                .dureeReel(promo.getDureeReel())
                .etat(promo.getEtat())
                .promoReferentiels(promo.getPromoReferentiels() != null
                        ? promo.getPromoReferentiels().stream()
                        .map(PromoReferentielDtoResponse::toDTO)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

    // Convert DTO back to Promo entity
    public static Promo toEntity(PromoDtoResponse promoDTO) {
        if (promoDTO == null) {
            return null;
        }

        Promo promo = new Promo();
        promo.setId(promoDTO.getId());
        promo.setLibelle(promoDTO.getLibelle());
        promo.setDateDebut(promoDTO.getDateDebut());
        promo.setDateFin(promoDTO.getDateFin());
        promo.setDureeReel(promoDTO.getDureeReel());
        promo.setEtat(promoDTO.getEtat());
        // Map promoReferentiels back to entity if needed
        return promo;
    }
}
