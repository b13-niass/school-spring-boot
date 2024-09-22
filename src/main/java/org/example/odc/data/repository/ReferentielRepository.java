package org.example.odc.data.repository;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.entity.Role;
import org.example.odc.enums.ReferentielStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ReferentielRepository extends JpaRepository<Referentiel, Long> {
    Optional<Referentiel> findByLibelle(String libelle);
    Optional<Referentiel> findByCode(String code);
    Collection<Referentiel> findAllByStatus(ReferentielStatusEnum status);
}