package org.example.odc.data.repository;

import org.example.odc.data.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    @Query("SELECT c FROM Competence c WHERE c.referentiel.id = :referentielId")
    Collection<Competence> findByReferentielId(@Param("referentielId") Long referentielId);
}
