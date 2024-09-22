package org.example.odc.web.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDTORequest {
    @NotEmpty(message = "Competence name cannot be empty")
    private String nom;

    @NotEmpty(message = "Competence description cannot be empty")
    private String description;

    @NotNull(message = "Competence acquisition duration is required")
    @Positive(message = "DureeAcquisition must be a positive number")
    private Integer dureeAcquisition;

    @NotEmpty(message = "Competence type cannot be empty")
    private String type;

    @Valid
    private List<ModuleDTORequest> modules;
}
