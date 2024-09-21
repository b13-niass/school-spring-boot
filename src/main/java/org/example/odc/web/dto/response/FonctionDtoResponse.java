package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Fonction;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FonctionDtoResponse {

    private Long id;
    private String libelle;
    private String description;

    // Representing related entities
    private List<UserDtoResponse> users;

    // Convert Fonction entity to DTO
    public static FonctionDtoResponse toDTO(Fonction fonction) {
        if (fonction == null) {
            return null;
        }

        return FonctionDtoResponse.builder()
                .id(fonction.getId())
                .libelle(fonction.getLibelle())
                .description(fonction.getDescription())
                .users(fonction.getUsers() != null
                        ? fonction.getUsers().stream().map(UserDtoResponse::toDTO).collect(Collectors.toList())
                        : null)
                .build();
    }

    // Convert DTO back to Fonction entity
    public static Fonction toEntity(FonctionDtoResponse fonctionDTO) {
        if (fonctionDTO == null) {
            return null;
        }

        Fonction fonction = new Fonction();
        fonction.setId(fonctionDTO.getId());
        fonction.setLibelle(fonctionDTO.getLibelle());
        fonction.setDescription(fonctionDTO.getDescription());
        // You can map users back to the entity if needed
        return fonction;
    }
}
