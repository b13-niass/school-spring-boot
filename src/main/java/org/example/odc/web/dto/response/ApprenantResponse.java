package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.EtatApprenant;
import org.example.odc.data.entity.Note;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.User;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprenantResponse {
    private String nomTuteur;
    private String prenomTuteur;
    private String contactTuteur;
    private String cniFile;
    private String extraitFile;
    private String diplomeFile;
    private String casierFile;
    private String photoCouverture;
    private String qrCode;

    private User user;
    private PromoReferentiel promoReferentiel;
    private EtatApprenant etatApprenant;
    private List<Note> notes;
}
