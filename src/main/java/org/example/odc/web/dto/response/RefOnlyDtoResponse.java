package org.example.odc.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.enums.ReferentielStatusEnum;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RefOnlyDtoResponse {

    @JsonIgnore
    private Long id;

    private String libelle;
    private String code;
    private String description;
    private String photoCouverture;
    private ReferentielStatusEnum status;
    @JsonIgnore
    private List<Competence> competences;
    @JsonIgnore
    private List<PromoReferentiel> promoReferentiels;
}

