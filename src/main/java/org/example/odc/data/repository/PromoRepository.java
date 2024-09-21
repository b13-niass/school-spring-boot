package org.example.odc.data.repository;

import org.example.odc.data.entity.Promo;
import org.example.odc.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Long> {
}