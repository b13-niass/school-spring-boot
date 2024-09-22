package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Promo;
import org.example.odc.data.entity.PromoReferentiel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PromoDtoResponse {

    private Long id;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeReel;
    private String etat;

    // List of PromoReferentielDtoResponse (or simply promo referentiel names/codes)
    private List<PromoReferentiel> promoReferentiels;

}
