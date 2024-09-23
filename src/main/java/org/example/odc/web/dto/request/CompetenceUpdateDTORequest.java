package org.example.odc.web.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.validator.annotation.NotEmptyIfPresent;
import org.example.odc.validator.annotation.UniqueCompetenceName;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceUpdateDTORequest {
    @NotEmpty(message = "Competence name cannot be empty")
    @UniqueCompetenceName
    private String nom;

    @NotEmpty(message = "Competence description cannot be empty")
    private String description;

    @NotNull(message = "Competence acquisition duration is required")
    @Positive(message = "DureeAcquisition must be a positive number")
    private Integer dureeAcquisition;

    @NotEmpty(message = "Competence type cannot be empty")
    private String type;

    @Valid
    @NotEmptyIfPresent(message = "Les modules ne peuvent pas être vides")
    private List<ModuleUpdateDTORequest> modules;
}
