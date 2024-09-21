package org.example.odc.data.repository;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferentielRepository extends JpaRepository<Referentiel, Long> {
}