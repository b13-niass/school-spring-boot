package org.example.odc.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.odc.validator.annotation.ActiveReferentiels;
import org.example.odc.validator.annotation.NoApprenantsForReferentielsToRemove;
import org.example.odc.validator.annotation.UniquePromoLibelle;
import org.example.odc.validator.annotation.ValidDateDebutFin;

import java.time.LocalDate;
import java.util.List;

@Data
//@ActiveReferentiels
public class PromoUpdateRefDTORequest {
    @JsonIgnore
    private Long promoId;

    private List<Long> referentielIdsToAdd;

    @NoApprenantsForReferentielsToRemove
    private List<Long> referentielIdsToRemove;
}
