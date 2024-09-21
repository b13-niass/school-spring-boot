package org.example.odc.data.repository;

import org.example.odc.data.entity.Emargement;
import org.example.odc.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmargementRepository extends JpaRepository<Emargement, Long> {
}