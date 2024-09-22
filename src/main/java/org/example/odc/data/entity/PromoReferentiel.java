package org.example.odc.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Promo promo;

    @ManyToOne
    @JsonIgnore
    private Referentiel referentiel;

    @OneToMany(mappedBy = "promoReferentiel")
    @JsonIgnore
    private List<Apprenant> apprenants;


    // Getters and Setters
}

