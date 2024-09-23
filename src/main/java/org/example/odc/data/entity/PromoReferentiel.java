package org.example.odc.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PromoReferentiel  extends BaseEntity {
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

