package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Note;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDtoResponse {

    private Long id;
    private String nom;
    private String description;
    private int dureeAcquisition;

    // Representing related entities
    private List<Note> notes; // You can use a simplified version if needed
//    private Competence competence; // Assuming you will create a CompetenceDtoResponse
}
