package org.example.odc.data.repository;

import org.example.odc.data.entity.Promo;

import java.util.Optional;

public interface PromoRepository extends SoftDeleteRepository<Promo, Long> {
    boolean existsByLibelle(String libelle);
    Optional<Promo> findByEtat(String etat);
    Optional<Promo> findByLibelle(String libelle);
}