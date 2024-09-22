package org.example.odc.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.Referentiel;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDtoResponse {

    private Long id;
    private String nom;
    private String description;
    private int dureeAcquisition;
    private String type;

    @JsonIgnore
    private Referentiel referentiel;

    private List<ModuleDtoResponse> modules;

}
