package org.example.odc.web.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.odc.enums.PromoEtatEnum;
import org.example.odc.validator.annotation.PromoEnumValidator;

@Data
public class PromoUpdateStatusDTORequest {
    @JsonIgnore
    private Long promoId;

    @NotNull(message = "Promo state is required")
    @PromoEnumValidator(enumClass = PromoEtatEnum.class, message = "Invalid promo state")
    private String etat;
}
