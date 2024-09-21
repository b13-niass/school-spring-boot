package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.EtatApprenant;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EtatApprenantDtoResponse {

    private Long id;
    private String etat;
    private String motif;

    // Representing related entities
    private List<ApprenantDtoResponse> apprenants;

    // Convert EtatApprenant entity to DTO
    public static EtatApprenantDtoResponse toDTO(EtatApprenant etatApprenant) {
        if (etatApprenant == null) {
            return null;
        }

        return EtatApprenantDtoResponse.builder()
                .id(etatApprenant.getId())
                .etat(etatApprenant.getEtat())
                .motif(etatApprenant.getMotif())
                .apprenants(etatApprenant.getApprenants() != null
                        ? etatApprenant.getApprenants().stream().map(ApprenantDtoResponse::toDTO).collect(Collectors.toList())
                        : null)
                .build();
    }

    // Convert DTO back to EtatApprenant entity
    public static EtatApprenant toEntity(EtatApprenantDtoResponse etatApprenantDTO) {
        if (etatApprenantDTO == null) {
            return null;
        }

        EtatApprenant etatApprenant = new EtatApprenant();
        etatApprenant.setId(etatApprenantDTO.getId());
        etatApprenant.setEtat(etatApprenantDTO.getEtat());
        etatApprenant.setMotif(etatApprenantDTO.getMotif());
        // You can map apprenants back to the entity if needed
        return etatApprenant;
    }
}
