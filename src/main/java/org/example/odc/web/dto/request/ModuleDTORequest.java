package org.example.odc.web.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTORequest {
    @NotEmpty(message = "Module name cannot be empty")
    private String nom;

    @NotEmpty(message = "Module description cannot be empty")
    private String description;

    @NotNull(message = "Module acquisition duration is required")
    @Positive(message = "DureeAcquisition must be a positive number")
    private Integer dureeAcquisition;
}
