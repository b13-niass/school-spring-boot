package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Note;
import org.example.odc.web.dto.response.NoteDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteResponseMapper {
    Note toEntity(NoteDtoResponse noteDtoResponse);
    NoteDtoResponse toDTO(Note note);
    List<NoteDtoResponse> toDTOList(List<Note> notes);
    List<Note> toEntityList(List<NoteDtoResponse> noteDtoResponses);
}
