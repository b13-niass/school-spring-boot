package org.example.odc.data.repository;

import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    boolean existsByNom(String nom);
    Optional<Module> findByCompetenceAndNom(Competence competence, String nom);
}
