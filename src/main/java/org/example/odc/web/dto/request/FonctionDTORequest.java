package org.example.odc.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FonctionDTORequest(
        @NotBlank(message = "Libelle est requis")
        String libelle,

        @NotBlank(message = "Description est requis")
        String description
) {
}
