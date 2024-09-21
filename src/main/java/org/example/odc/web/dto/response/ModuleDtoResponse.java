package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Module;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDtoResponse {

    private Long id;
    private String nom;
    private String description;
    private int dureeAcquisition;

    // Representing related entities
    private List<NoteDtoResponse> notes; // You can use a simplified version if needed
    private CompetenceDtoResponse competence; // Assuming you will create a CompetenceDtoResponse

    // Convert Module entity to DTO
    public static ModuleDtoResponse toDTO(Module module) {
        if (module == null) {
            return null;
        }

        return ModuleDtoResponse.builder()
                .id(module.getId())
                .nom(module.getNom())
                .description(module.getDescription())
                .dureeAcquisition(module.getDureeAcquisition())
                .notes(module.getNotes() != null
                        ? module.getNotes().stream().map(NoteDtoResponse::toDTO).collect(Collectors.toList())
                        : null)
                .competence(module.getCompetence() != null
                        ? CompetenceDtoResponse.toDTO(module.getCompetence())
                        : null)
                .build();
    }

    // Convert DTO back to Module entity
    public static Module toEntity(ModuleDtoResponse moduleDTO) {
        if (moduleDTO == null) {
            return null;
        }

        Module module = new Module();
        module.setId(moduleDTO.getId());
        module.setNom(moduleDTO.getNom());
        module.setDescription(moduleDTO.getDescription());
        module.setDureeAcquisition(moduleDTO.getDureeAcquisition());
        // Map notes and competence back to entity if needed
        return module;
    }
}
