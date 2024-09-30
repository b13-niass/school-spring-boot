package org.example.odc.data.repository;

import org.example.odc.data.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    @Query("SELECT a FROM Apprenant a WHERE a.user.nom = :nom AND a.user.prenom = :prenom AND a.user.email = :email")
    Optional<Apprenant> findByNomPrenomAndEmail(@Param("nom") String nom,
                                                @Param("prenom") String prenom,
                                                @Param("email") String email);
}