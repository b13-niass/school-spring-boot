package org.example.odc.web.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.validator.annotation.NotEmptyIfPresent;
import org.example.odc.validator.annotation.UniqueRefCode;
import org.example.odc.validator.annotation.UniqueRefLibelle;
import org.example.odc.validator.annotation.ValidRefEnum;

import java.util.List;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReferentielUpdateDTORequest {

    @UniqueRefLibelle(message = "Le libelle doit etre unique")
    private String libelle;

    @UniqueRefCode(message = "Le code doit etre unique")
    private String code;

    private String description;

    private String photoCouverture;

    @ValidRefEnum(enumClass = ReferentielStatusEnum.class, message = "Status must be one of: ACTIF, INACTIF, ARCHIVER")
    private String status;

    @Valid
    @NotEmptyIfPresent(message = "Les compétences ne peuvent pas être vides")
    private List<CompetenceUpdateDTORequest> competences;

}
