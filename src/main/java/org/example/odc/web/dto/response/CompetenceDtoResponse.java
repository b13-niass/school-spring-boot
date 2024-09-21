package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Competence;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDtoResponse {

    private Long id;
    private String nom;
    private String description;
    private int dureeAcquisition;
    private String type;

    // Representing related PromoReferentiel entity
    private PromoReferentielDtoResponse promoReferentiel;

    // Convert Competence entity to DTO
    public static CompetenceDtoResponse toDTO(Competence competence) {
        if (competence == null) {
            return null;
        }

        return CompetenceDtoResponse.builder()
                .id(competence.getId())
                .nom(competence.getNom())
                .description(competence.getDescription())
                .dureeAcquisition(competence.getDureeAcquisition())
                .type(competence.getType())
                .promoReferentiel(competence.getPromoReferentiel() != null
                        ? PromoReferentielDtoResponse.toDTO(competence.getPromoReferentiel())
                        : null)
                .build();
    }

    // Convert DTO back to Competence entity
    public static Competence toEntity(CompetenceDtoResponse competenceDTO) {
        if (competenceDTO == null) {
            return null;
        }

        Competence competence = new Competence();
        competence.setId(competenceDTO.getId());
        competence.setNom(competenceDTO.getNom());
        competence.setDescription(competenceDTO.getDescription());
        competence.setDureeAcquisition(competenceDTO.getDureeAcquisition());
        competence.setType(competenceDTO.getType());
        competence.setPromoReferentiel(competenceDTO.getPromoReferentiel() != null
                ? PromoReferentielDtoResponse.toEntity(competenceDTO.getPromoReferentiel())
                : null);
        return competence;
    }
}
