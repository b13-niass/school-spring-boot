package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Apprenant;
import org.example.odc.data.entity.EtatApprenant;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EtatApprenantDtoResponse {

    private Long id;
    private String etat;
    private String motif;
    private List<Apprenant> apprenants;

}
