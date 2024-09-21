package org.example.odc.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @MapsId("promoId")
    @JoinColumn(name = "promo_id")
    private Promo promo;

    @ManyToOne
    @MapsId("referentielId")
    @JoinColumn(name = "referentiel_id")
    private Referentiel referentiel;

    // Getters and Setters
}

