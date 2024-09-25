package org.example.odc.data.repository;

import org.example.odc.data.entity.Promo;
import org.example.odc.data.entity.Role;
import org.example.odc.enums.PromoEtatEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoRepository extends SoftDeleteRepository<Promo, Long> {
    boolean existsByLibelle(String libelle);
    Optional<Promo> findByEtat(String etat);
}