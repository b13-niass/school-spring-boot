package org.example.odc.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.odc.enums.PromoEtatEnum;
import org.example.odc.validator.annotation.UniquePromoLibelle;
import org.example.odc.validator.annotation.ValidDureeOuDateFin;
import org.example.odc.validator.annotation.ValidReferentielIds;

import java.time.LocalDate;
import java.util.List;

@Data
@ValidDureeOuDateFin(message = "la dateFin ou la dureeReel doit etre donnée.")
public class PromoDTORequest {

    @NotNull(message = "Libelle is mandatory")
    @UniquePromoLibelle
    private String libelle;

    @NotNull(message = "Date de début is mandatory")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    private int dureeReel;

    private PromoEtatEnum etat = PromoEtatEnum.ACTIF;

    @ValidReferentielIds
    private List<Long> referentielIds;

}
