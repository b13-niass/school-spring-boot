package org.example.odc.data.repository;

import org.example.odc.data.entity.EtatApprenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatApprenantRepository extends JpaRepository<EtatApprenant, Long> {
}