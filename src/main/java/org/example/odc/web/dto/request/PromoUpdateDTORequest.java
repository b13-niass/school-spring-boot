package org.example.odc.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.odc.enums.PromoEtatEnum;
import org.example.odc.validator.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Data
@ValidDateDebutFin(message = "dsdsdsdds")
public class PromoUpdateDTORequest {

    @Size(min = 3, max = 255, message = "Libelle must be between 3 and 255 characters")
    @UniquePromoLibelle
    private String libelle;

    @FutureOrPresent(message = "Date de début must be in the future or present")
    private LocalDate dateDebut;

    @Future(message = "Date de fin must be in the future")
    private LocalDate dateFin;

    @Positive
    private Integer dureeReel;

    @Pattern(regexp = "ACTIF|CLOTURE|INACTIF", message = "Etat must be either 'Actif', 'Cloture' or 'Inactif'")
    private String etat;
}
