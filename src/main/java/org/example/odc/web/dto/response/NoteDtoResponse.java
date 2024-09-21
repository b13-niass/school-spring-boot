package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    // Convert Note entity to DTO
    public static NoteDtoResponse toDTO(Note note) {
        if (note == null) {
            return null;
        }

        return NoteDtoResponse.builder()
                .id(note.getId())
                .note(note.getNote())
                .apprenant(note.getApprenant() != null
                        ? ApprenantDtoResponse.toDTO(note.getApprenant())
                        : null)
                .module(note.getModule() != null
                        ? ModuleDtoResponse.toDTO(note.getModule())
                        : null)
                .build();
    }

    // Convert DTO back to Note entity
    public static Note toEntity(NoteDtoResponse noteDTO) {
        if (noteDTO == null) {
            return null;
        }

        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setNote(noteDTO.getNote());
        note.setApprenant(noteDTO.getApprenant() != null
                ? ApprenantDtoResponse.toEntity(noteDTO.getApprenant())
                : null);
        note.setModule(noteDTO.getModule() != null
                ? ModuleDtoResponse.toEntity(noteDTO.getModule())
                : null);
        return note;
    }
}
