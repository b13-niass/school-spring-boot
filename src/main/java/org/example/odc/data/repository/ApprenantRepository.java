package org.example.odc.data.repository;

import org.example.odc.data.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
}