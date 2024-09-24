package org.example.odc.data.repository;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.entity.Role;
import org.example.odc.enums.ReferentielStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReferentielRepository extends SoftDeleteRepository<Referentiel, Long> {
    Optional<Referentiel> findByLibelle(String libelle);
    Optional<Referentiel> findByCode(String code);
    Collection<Referentiel> findByDeletedFalseAndStatus(ReferentielStatusEnum status);
    @Override
    default void delete(Referentiel referentiel) {
        referentiel.setDeleted(true);
        referentiel.setDeletedAt(LocalDateTime.now());
        save(referentiel);
    }
    boolean existsByLibelle(String libelle);
    boolean existsByCode(String code);
    boolean existsByIdIn(List<Long> ids);
    boolean existsByIdInAndStatusIs(List<Long> ids, ReferentielStatusEnum status);

    @Query("SELECT CASE WHEN r.status = 'ACTIF' THEN true ELSE false END " +
            "FROM Referentiel r WHERE r.id = :referentielId")
    boolean existsByIdAndStatusActive(Long referentielId);

}