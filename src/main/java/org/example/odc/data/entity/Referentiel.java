package org.example.odc.data.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.odc.enums.ReferentielStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Referentiel extends BaseEntity{
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

    @OneToMany(mappedBy = "referentiel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competence> competences;

}


