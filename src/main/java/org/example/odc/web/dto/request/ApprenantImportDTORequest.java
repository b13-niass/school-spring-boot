package org.example.odc.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.example.odc.validator.annotation.ValidBaseTel;
import org.example.odc.validator.annotation.ValidEmail;
import org.example.odc.validator.annotation.ValidTelephone;
import org.springframework.web.multipart.MultipartFile;

@Builder(toBuilder = true)
public record ApprenantImportDTORequest(
        @NotBlank(message = "Nom du tuteur est requis")
        String nomTuteur,

        @NotBlank(message = "Prénom du tuteur est requis")
        String prenomTuteur,

        @NotBlank(message = "Contact du tuteur est requis")
        @ValidBaseTel
        String contactTuteur,

        @NotNull(message = "Fichier CNI est requis")
        MultipartFile cniFile,

        @NotNull(message = "Fichier extrait est requis")
        MultipartFile extraitFile,

        @NotNull(message = "Fichier diplôme est requis")
        MultipartFile diplomeFile,

        @NotNull(message = "Fichier casier est requis")
        MultipartFile casierFile,

        @NotNull(message = "Photo de couverture est requise")
        MultipartFile photoCouverture,

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

        @NotNull(message = "la photo est requise")
        MultipartFile photo,

        @NotBlank(message = "Téléphone est requis")
        @ValidTelephone
        String telephone,

        @NotNull(message = "Fonction est requis")
        String fonctionId,

        @NotNull(message = "Référentiel de promo est requis")
        String referentiel // A tester

) {
}
