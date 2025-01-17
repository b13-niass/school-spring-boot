package org.example.odc.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.validator.annotation.NotEmptyIfPresent;
import org.example.odc.validator.annotation.UniqueRefCode;
import org.example.odc.validator.annotation.UniqueRefLibelle;
import org.example.odc.validator.annotation.ValidRefEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReferentielDTORequest {
    @NotEmpty(message = "Libelle cannot be empty")
    @UniqueRefLibelle(message = "Le libelle doit etre unique")
    private String libelle;

    @NotEmpty(message = "Code cannot be empty")
    @UniqueRefCode(message = "Le code doit etre unique")
    private String code;
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull(message = "Photo couverture must be provided")
//    private MultipartFile photoCouverture;
    private String photoCouverture;

    @NotEmpty(message = "Status cannot be empty")
    @ValidRefEnum(enumClass = ReferentielStatusEnum.class, message = "Status must be one of: ACTIF, INACTIF, ARCHIVER")
    private String status;

    @Valid
    @NotEmptyIfPresent(message = "Les compétences ne peuvent pas être vides")
    private List<CompetenceDTORequest> competences;

}
