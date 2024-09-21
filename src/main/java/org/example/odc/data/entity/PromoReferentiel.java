package org.example.odc.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PromoReferentiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Promo promo;

    @ManyToOne
    private Referentiel referentiel;

    @OneToMany(mappedBy = "promoReferentiel")
    private List<Apprenant> apprenants;

    @OneToMany(mappedBy = "promoReferentiel")
    private List<Competence> competences;

    // Getters and Setters
}

