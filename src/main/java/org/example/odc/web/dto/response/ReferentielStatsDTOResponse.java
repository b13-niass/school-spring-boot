package org.example.odc.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class ReferentielStatsDTOResponse {
        private Long id;
        private String libelle;
        private Long apprenantCount;
}
