package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.*;

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

    private User user;
    private PromoReferentiel promoReferentiel;
    private EtatApprenant etatApprenant;
    private List<Note> notes;

}
