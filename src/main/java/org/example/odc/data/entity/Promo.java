package org.example.odc.data.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String libelle;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int dureeReel;
    private String etat; // Actif, Cloture, Inactif

    @OneToMany(mappedBy = "promo", cascade = CascadeType.ALL)
    private List<PromoReferentiel> promoReferentiels;

    // Getters and Setters
}

