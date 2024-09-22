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
public class PromoReferentielDtoResponse {

    private Long id;

    // Representing the promo and referentiel with simplified fields or their own DTOs
    private Promo promo;
    private Referentiel referentiel;

    private List<Apprenant> apprenants;
//    private List<Competence> competences;

}
