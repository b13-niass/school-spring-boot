package org.example.odc.data.repository;

import org.example.odc.data.entity.Note;
import org.example.odc.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
