package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.enums.ReferentielStatusEnum;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReferentielDtoResponse {

    private Long id;
    private String libelle;
    private String code;
    private String description;
    private String photoCouverture;
    private ReferentielStatusEnum status;

    // If needed, a list of PromoReferentielDTO to avoid cyclic issues
    private List<PromoReferentielDtoResponse> promoReferentiels;

    // Convert Referentiel entity to DTO
    public static ReferentielDtoResponse toDTO(Referentiel referentiel) {
        if (referentiel == null) {
            return null;
        }

        return ReferentielDtoResponse.builder()
                .id(referentiel.getId())
                .libelle(referentiel.getLibelle())
                .code(referentiel.getCode())
                .description(referentiel.getDescription())
                .photoCouverture(referentiel.getPhotoCouverture())
                .status(referentiel.getStatus())
                .promoReferentiels(referentiel.getPromoReferentiels() != null
                        ? referentiel.getPromoReferentiels().stream()
                        .map(PromoReferentielDtoResponse::toDTO)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

    // Convert Referentiel DTO to Entity
    public static Referentiel toEntity(ReferentielDtoResponse referentielDTO) {
        if (referentielDTO == null) {
            return null;
        }

        Referentiel referentiel = new Referentiel();
        referentiel.setId(referentielDTO.getId());
        referentiel.setLibelle(referentielDTO.getLibelle());
        referentiel.setCode(referentielDTO.getCode());
        referentiel.setDescription(referentielDTO.getDescription());
        referentiel.setPhotoCouverture(referentielDTO.getPhotoCouverture());
        referentiel.setStatus(referentielDTO.getStatus());

        // Convert promoReferentiels DTO back to PromoReferentiel entities if needed
        // referentiel.setPromoReferentiels(referentielDTO.getPromoReferentiels() != null
        //         ? referentielDTO.getPromoReferentiels().stream()
        //             .map(PromoReferentielDtoResponse::toEntity)
        //             .collect(Collectors.toList())
        //         : null);

        return referentiel;
    }
}

