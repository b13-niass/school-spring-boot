package org.example.odc.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.example.odc.validator.annotation.ValidEmail;
import org.example.odc.validator.annotation.ValidTelephone;
import org.springframework.web.multipart.MultipartFile;

@Builder(toBuilder = true)
public record UserDTORequest(
        @NotBlank(message = "Nom est requis")
        String nom,

        @NotBlank(message = "Prénom est requis")
        String prenom,

        @NotBlank(message = "Adresse est requis")
        String adresse,

        @NotBlank(message = "Email est requis")
        @ValidEmail
        String email,

        @NotNull
        @Positive
        Long roleId,

        @NotBlank(message = "Password est requis")
        String password,

        @NotBlank(message = "Status est requis")
        String status,

        @NotNull(message = "la photo est requise")
        MultipartFile photo,

        @NotBlank(message = "Téléphone est requis")
        @ValidTelephone
        String telephone,

        @NotNull(message = "Fonction est requis")
        String fonctionId // à vérifier
) {
}
