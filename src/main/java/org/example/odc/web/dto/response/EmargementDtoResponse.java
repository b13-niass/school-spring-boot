package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Emargement;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmargementDtoResponse {

    private Long id;
    private LocalDateTime entree;
    private LocalDateTime sortie;

    // Representing related User entity
    private UserDtoResponse user;

    // Convert Emargement entity to DTO
    public static EmargementDtoResponse toDTO(Emargement emargement) {
        if (emargement == null) {
            return null;
        }

        return EmargementDtoResponse.builder()
                .id(emargement.getId())
                .entree(emargement.getEntree())
                .sortie(emargement.getSortie())
                .user(emargement.getUser() != null ? UserDtoResponse.toDTO(emargement.getUser()) : null)
                .build();
    }

    // Convert DTO back to Emargement entity
    public static Emargement toEntity(EmargementDtoResponse emargementDTO) {
        if (emargementDTO == null) {
            return null;
        }

        Emargement emargement = new Emargement();
        emargement.setId(emargementDTO.getId());
        emargement.setEntree(emargementDTO.getEntree());
        emargement.setSortie(emargementDTO.getSortie());
        emargement.setUser(emargementDTO.getUser() != null ? UserDtoResponse.toEntity(emargementDTO.getUser()) : null);
        return emargement;
    }
}
