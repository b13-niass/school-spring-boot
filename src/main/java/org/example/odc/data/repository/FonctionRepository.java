package org.example.odc.data.repository;

import org.example.odc.data.entity.Fonction;
import org.example.odc.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FonctionRepository extends JpaRepository<Fonction, Long> {
}