package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Apprenant;
import org.example.odc.data.entity.Note;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NoteDtoResponse {

    private Long id;
    private double note;

    // Representing the Apprenant and Module with simplified fields or their own DTOs
    private ApprenantDtoResponse apprenant;
    private ModuleDtoResponse module;

}
