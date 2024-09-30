package org.example.odc.web.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Apprenant;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprenantImportDtoResponse {
   public List<Apprenant> apprenantsInscrit;
    public List<Apprenant> apprenantsNonInscrit;
}
