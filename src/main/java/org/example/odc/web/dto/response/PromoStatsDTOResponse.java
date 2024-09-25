package org.example.odc.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@Data
public class PromoStatsDTOResponse {
    private Long id;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long totalLearners;
    private Long activeLearners;
    private Long inactiveLearners;
    private List<ReferentielStatsDTOResponse> referentielStats;

}
