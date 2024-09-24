package org.example.odc.data.repository;

import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromoReferentielRepository extends JpaRepository<PromoReferentiel, Long> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Apprenant a WHERE a.promoReferentiel.referentiel.id = :referentielId")
    boolean existsByReferentielIdAndApprenantsNotEmpty(Long referentielId);

    // Vérifier si un référentiel est déjà associé à une promotion
    @Query("SELECT CASE WHEN COUNT(pr) > 0 THEN true ELSE false END " +
            "FROM PromoReferentiel pr WHERE pr.promo.id = :promoId AND pr.referentiel.id = :referentielId")
    boolean existsByPromoIdAndReferentielId(Long promoId, Long referentielId);
}