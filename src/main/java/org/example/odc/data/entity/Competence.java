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
public class Competence extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    private String description;
    private int dureeAcquisition;
    private String type;

    @ManyToOne
    @JsonIgnore
    private Referentiel referentiel;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> modules;

}

