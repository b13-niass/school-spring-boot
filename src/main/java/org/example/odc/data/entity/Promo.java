package org.example.odc.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String libelle;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeReel;
    private String etat; // Actif, Cloture, Inactif

    @OneToMany(mappedBy = "promo")
    private List<PromoReferentiel> promoReferentiels;

    // Getters and Setters
}

