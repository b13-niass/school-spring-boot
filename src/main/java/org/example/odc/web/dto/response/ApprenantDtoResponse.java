package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Apprenant;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApprenantDtoResponse {

    private Long id;
    private String nomTuteur;
    private String prenomTuteur;
    private String contactTuteur;
    private String cniFile;
    private String extraitFile;
    private String diplomeFile;
    private String casierFile;
    private String photoCouverture;

    // Representing related entities
    private UserDtoResponse user;
    private PromoReferentielDtoResponse promoReferentiel;
    private EtatApprenantDtoResponse etatApprenant;
    private List<NoteDtoResponse> notes;

    // Convert Apprenant entity to DTO
    public static ApprenantDtoResponse toDTO(Apprenant apprenant) {
        if (apprenant == null) {
            return null;
        }

        return ApprenantDtoResponse.builder()
                .id(apprenant.getId())
                .nomTuteur(apprenant.getNomTuteur())
                .prenomTuteur(apprenant.getPrenomTuteur())
                .contactTuteur(apprenant.getContactTuteur())
                .cniFile(apprenant.getCniFile())
                .extraitFile(apprenant.getExtraitFile())
                .diplomeFile(apprenant.getDiplomeFile())
                .casierFile(apprenant.getCasierFile())
                .photoCouverture(apprenant.getPhotoCouverture())
                .user(apprenant.getUser() != null ? UserDtoResponse.toDTO(apprenant.getUser()) : null)
                .promoReferentiel(apprenant.getPromoReferentiel() != null
                        ? PromoReferentielDtoResponse.toDTO(apprenant.getPromoReferentiel()) : null)
                .etatApprenant(apprenant.getEtatApprenant() != null
                        ? EtatApprenantDtoResponse.toDTO(apprenant.getEtatApprenant()) : null)
                .notes(apprenant.getNotes() != null
                        ? apprenant.getNotes().stream().map(NoteDtoResponse::toDTO).collect(Collectors.toList()) : null)
                .build();
    }

    // Convert DTO back to Apprenant entity
    public static Apprenant toEntity(ApprenantDtoResponse apprenantDTO) {
        if (apprenantDTO == null) {
            return null;
        }

        Apprenant apprenant = new Apprenant();
        apprenant.setId(apprenantDTO.getId());
        apprenant.setNomTuteur(apprenantDTO.getNomTuteur());
        apprenant.setPrenomTuteur(apprenantDTO.getPrenomTuteur());
        apprenant.setContactTuteur(apprenantDTO.getContactTuteur());
        apprenant.setCniFile(apprenantDTO.getCniFile());
        apprenant.setExtraitFile(apprenantDTO.getExtraitFile());
        apprenant.setDiplomeFile(apprenantDTO.getDiplomeFile());
        apprenant.setCasierFile(apprenantDTO.getCasierFile());
        apprenant.setPhotoCouverture(apprenantDTO.getPhotoCouverture());
        apprenant.setUser(apprenantDTO.getUser() != null ? UserDtoResponse.toEntity(apprenantDTO.getUser()) : null);
        apprenant.setPromoReferentiel(apprenantDTO.getPromoReferentiel() != null
                ? PromoReferentielDtoResponse.toEntity(apprenantDTO.getPromoReferentiel()) : null);
        apprenant.setEtatApprenant(apprenantDTO.getEtatApprenant() != null
                ? EtatApprenantDtoResponse.toEntity(apprenantDTO.getEtatApprenant()) : null);
        // Set notes if necessary, or handle them similarly as above
        return apprenant;
    }
}
