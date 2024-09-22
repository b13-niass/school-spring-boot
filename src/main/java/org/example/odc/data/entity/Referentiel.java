package org.example.odc.data.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.enums.ReferentielStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Referentiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String libelle;

    @Column(unique = true)
    private String code;

    private String description;
    private String photoCouverture;

    @OneToMany(mappedBy = "referentiel")
//    @JsonIgnore
    private List<PromoReferentiel> promoReferentiels;

    @Enumerated(EnumType.STRING)
    private ReferentielStatusEnum status;

    @OneToMany(mappedBy = "referentiel")
    private List<Competence> competences;

    @Column(nullable = false)
    private boolean deleted = false;

    private LocalDateTime deletedAt;
}


